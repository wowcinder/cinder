package xdata.etl.cinder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public class KafkaConsumerManagerImpl implements KafkaConsumerManager {
	private final ConsumerConfig kafkaClientConfig;
	private final Map<KafkaWatchDogTopicSetting, ConsumerConnector> connectors;

	public KafkaConsumerManagerImpl(ConsumerConfig kafkaClientConfig) {
		this.kafkaClientConfig = kafkaClientConfig;
		this.connectors = new HashMap<KafkaWatchDogTopicSetting, ConsumerConnector>();
	}

	@Override
	public boolean createConsumer(KafkaWatchDogTopicSetting topicSetting) {
		if (connectors.containsKey(topicSetting)) {
			return false;
		}
		ConsumerConnector connector = kafka.consumer.Consumer
				.createJavaConsumerConnector(kafkaClientConfig);
		connectors.put(topicSetting, connector);

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topicSetting.getTopic().getName(),
				topicSetting.getThreadNum());
		Map<String, List<KafkaStream<Message>>> consumerMap = connector
				.createMessageStreams(topicCountMap);
		ExecutorService executor = Executors.newFixedThreadPool(topicSetting
				.getThreadNum());
		List<KafkaStream<Message>> streams = consumerMap.get(topicSetting
				.getTopic().getName());
		// TODO
		return true;
	}

	public ConsumerConfig getKafkaClientConfig() {
		return kafkaClientConfig;
	}

	public Map<KafkaWatchDogTopicSetting, ConsumerConnector> getConnectors() {
		return connectors;
	}

}
