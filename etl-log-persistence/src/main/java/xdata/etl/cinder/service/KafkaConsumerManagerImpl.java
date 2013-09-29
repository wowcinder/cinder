package xdata.etl.cinder.service;

import java.util.HashMap;
import java.util.Map;

import kafka.consumer.ConsumerConfig;

import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public class KafkaConsumerManagerImpl implements KafkaConsumerManager {
	private final ConsumerConfig kafkaClientConfig;
	private final Map<KafkaWatchDogTopicSetting, ConsumerConnectorHolder> connectors;
	@Autowired
	private LogModelTransformerManager transformerManager;

	public KafkaConsumerManagerImpl(ConsumerConfig kafkaClientConfig) {
		this.kafkaClientConfig = kafkaClientConfig;
		this.connectors = new HashMap<KafkaWatchDogTopicSetting, ConsumerConnectorHolder>();
	}

	@Override
	public synchronized boolean startConsumer(
			KafkaWatchDogTopicSetting topicSetting) {
		if (connectors.containsKey(topicSetting)) {
			return false;
		}
		ConsumerConnectorHolder connectorHolder = new ConsumerConnectorHolder(
				kafkaClientConfig, topicSetting, transformerManager);
		connectors.put(topicSetting, connectorHolder);
		connectorHolder.run();
		return true;
	}

	@Override
	public synchronized boolean stopConsumer(
			KafkaWatchDogTopicSetting topicSetting) {
		if (!connectors.containsKey(topicSetting)) {
			return true;
		}
		ConsumerConnectorHolder connectorHolder = connectors.get(topicSetting);
		connectorHolder.shutdown();
		connectors.remove(topicSetting);
		return false;
	}

	@Override
	public boolean restartConsumer(KafkaWatchDogTopicSetting topicSetting) {
		if (stopConsumer(topicSetting)) {
			return startConsumer(topicSetting);
		}
		return false;
	}

	public ConsumerConfig getKafkaClientConfig() {
		return kafkaClientConfig;
	}

	public Map<KafkaWatchDogTopicSetting, ConsumerConnectorHolder> getConnectors() {
		return connectors;
	}

}
