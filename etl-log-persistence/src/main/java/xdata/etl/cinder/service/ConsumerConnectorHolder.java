package xdata.etl.cinder.service;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;
import kafka.utils.Utils;
import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.logmodel.transformer.LogModelTransformer;

public class ConsumerConnectorHolder {
	private final LogModelTransformerManager transformerManager;
	private final ConsumerConfig kafkaClientConfig;
	private ConsumerConnector connector;
	private final KafkaWatchDogTopicSetting topicSetting;
	private ExecutorService executor;

	public ConsumerConnectorHolder(ConsumerConfig kafkaClientConfig,
			KafkaWatchDogTopicSetting topicSetting,
			LogModelTransformerManager transformerManager) {
		this.topicSetting = topicSetting;
		this.kafkaClientConfig = kafkaClientConfig;
		this.transformerManager = transformerManager;
	}

	public void run() {
		this.connector = kafka.consumer.Consumer
				.createJavaConsumerConnector(kafkaClientConfig);
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topicSetting.getTopic().getName(),
				topicSetting.getThreadNum());
		Map<String, List<KafkaStream<Message>>> consumerMap = connector
				.createMessageStreams(topicCountMap);
		executor = Executors.newFixedThreadPool(topicSetting.getThreadNum());
		List<KafkaStream<Message>> streams = consumerMap.get(topicSetting
				.getTopic().getName());

		LogModelTransformer<?> transformer = transformerManager.getTransformer(
				topicSetting.getTopic().getVersion().getModel().getName(),
				topicSetting.getTopic().getVersion().getVersion());

		for (KafkaStream<Message> stream : streams) {
			executor.submit(new ProcessRunnable(stream, topicSetting.getTopic()
					.getCharset().getCharset(), transformer));
		}
	}

	public void shutdown() {
		if (connector != null) {
			connector.shutdown();
			connector = null;
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (executor != null && !executor.isShutdown()) {
			executor.shutdown();
			executor = null;
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static class ProcessRunnable implements Runnable {
		private KafkaStream<Message> stream;
		private final String charset;
		private final LogModelTransformer<?> transformer;

		public ProcessRunnable(KafkaStream<Message> stream, String charset,
				LogModelTransformer<?> transformer) {
			this.stream = stream;
			this.charset = charset;
			this.transformer = transformer;
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
			}
		}

		protected void process(String raw) {
			Map<String, List<HbaseRecord<String>>> recordMap = transformer
					.transform(raw);
			recordMap.clear();
			// TODO
		}

		public String getCharset() {
			return this.charset;
		}
	}
}
