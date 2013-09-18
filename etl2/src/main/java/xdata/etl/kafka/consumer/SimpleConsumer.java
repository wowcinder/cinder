package xdata.etl.kafka.consumer;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;
import kafka.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.kafka.KafkaContext;
import xdata.etl.kafka.exception.KafkaTopicProcessException;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.exception.TerminalFieldValueParseException;
import xdata.etl.kafka.process.KafkaTopicProcess;
import xdata.etl.kafka.util.KafkaRecordCharsetUtil;

public class SimpleConsumer implements IConsumer {

	public static final Logger logger = LoggerFactory
			.getLogger(SimpleConsumer.class);

	private HbaseContext hbaseCxt;
	private KafkaContext kafkaCxt;
	private Map<String, Integer> topicCountMap;
	private Integer threadCount = new Integer(0);
	private ExecutorService executor;
	private ConsumerConnector consumerConnector;

	public SimpleConsumer(Map<String, Integer> topicCountMap) {
		setTopicCountMap(topicCountMap);
	}

	private void setTopicCountMap(Map<String, Integer> topicCountMap) {
		this.topicCountMap = topicCountMap;
		for (Integer count : topicCountMap.values()) {
			threadCount += count;
		}
	}

	public void run() {
		consumerConnector = kafkaCxt.getKafkaConsumer();

		Map<String, List<KafkaStream<Message>>> consumerMap = consumerConnector
				.createMessageStreams(topicCountMap);
		executor = Executors.newFixedThreadPool(getThreadCount());

		for (String topic : consumerMap.keySet()) {
			List<KafkaStream<Message>> streams = consumerMap.get(topic);
			KafkaTopicProcess process = getKafkaCxt().getTopicProcessCache()
					.get(topic);
			for (KafkaStream<Message> stream : streams) {
				executor.submit(new ProcessRunnable(process, stream));
			}
		}
	}

	public void shutdown() {
		logger.info("consumer is shutdowning");
		if (consumerConnector != null) {
			consumerConnector.shutdown();
			consumerConnector = null;
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (executor != null && !executor.isShutdown()) {
			executor.shutdown();
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getKafkaCxt().getTopicProcessCache().shutdown();
	}

	public HbaseContext getHbaseCxt() {
		return hbaseCxt;
	}

	public void setHbaseCxt(HbaseContext hbaseCxt) {
		this.hbaseCxt = hbaseCxt;
	}

	public KafkaContext getKafkaCxt() {
		return kafkaCxt;
	}

	public void setKafkaCxt(KafkaContext kafkaCxt) {
		this.kafkaCxt = kafkaCxt;
	}

	public Integer getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(Integer threadCount) {
		this.threadCount = threadCount;
	}

	public static class ProcessRunnable implements Runnable {
		private KafkaTopicProcess process;
		private KafkaStream<Message> stream;
		private String charset = "UTF-8";

		public ProcessRunnable(KafkaTopicProcess process,
				KafkaStream<Message> stream) {
			this.process = process;
			this.stream = stream;
			this.charset = KafkaRecordCharsetUtil
					.getCharset(process.getTopic());
		}

		@Override
		public void run() {
			try {
				ConsumerIterator<Message> it = stream.iterator();
				while (it.hasNext()) {
					MessageAndMetadata<Message> messageAndMetadata = it.next();
					ByteBuffer bb = messageAndMetadata.message().payload();
					String raw = Utils.toString(bb, getCharset());
					process(raw);
				}
			} catch (Exception e) {
				logger.error("This is a fatal error\t" + process.getTopic(), e);
			}
		}

		protected void process(String raw) {
			try {
				process.process(raw);
				logger.debug("topic:" + process.getTopic() + "," + raw);
			} catch (KafkaTopicProcessException e1) {
				logger.error(
						process.getTopic() + "\t" + raw + "\t"
								+ e1.getMessage(), e1);
			} catch (KafkaTransformException e1) {
				if (e1 instanceof TerminalFieldValueParseException) {
					TerminalFieldValueParseException e = (TerminalFieldValueParseException) e1;
					logger.error(process.getTopic() + "\t" + raw + "\t"
							+ e.getTargetField().getType().getName() + "\t"
							+ e.getFieldStr());
				} else {
					logger.error(
							process.getTopic() + "\t" + raw + "\t"
									+ e1.getMessage(), e1);
				}
			} catch (Exception e) {
				logger.error("This is a fatal error\t" + process.getTopic()
						+ "\t" + raw, e);
			}
		}

		public String getCharset() {
			return this.charset;
		}
	}
}
