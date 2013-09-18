package xdata.etl.kafka.consumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.kafka.KafkaContext;

@Component
public class ConsumerFactory {

	public static final Logger logger = LoggerFactory
			.getLogger(ConsumerFactory.class);

	@Resource
	private HbaseContext hbaseCxt;
	@Resource
	private KafkaContext kafkaCxt;

	private Integer threadCount = 1;

	public ConsumerFactory() {
	}

	public IConsumer createConsumer(Map<String, Integer> topicCountMap) {
		SimpleConsumer c = new SimpleConsumer(topicCountMap);
		c.setHbaseCxt(hbaseCxt);
		c.setKafkaCxt(kafkaCxt);
		return c;
	}

	public IConsumer createConsumer(String topic, Integer threadCount) {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, threadCount);
		return createConsumer(topicCountMap);
	}

	public IConsumer createAllTopicConsumer() {
		return createAllTopicConsumer(getThreadCount());
	}

	public IConsumer createAllTopicConsumer(Integer threadCount) {
		Set<String> topics = kafkaCxt.getTopicManager().getTopics();
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		for (String topic : topics) {
			topicCountMap.put(topic, threadCount);
		}
		return createConsumer(topicCountMap);
	}

	public IConsumer createConsumer(String... topics) {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		for (String topic : topics) {
			topicCountMap.put(topic, getThreadCount());
		}
		return createConsumer(topicCountMap);
	}

	public boolean isExsits(String topic) {
		return kafkaCxt.getTopicManager().getClazz(topic) != null;
	}

	public HbaseContext getHbaseCxt() {
		return hbaseCxt;
	}

	public KafkaContext getKafkaCxt() {
		return kafkaCxt;
	}

	public void setHbaseCxt(HbaseContext hbaseCxt) {
		this.hbaseCxt = hbaseCxt;
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

}
