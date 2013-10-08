package xdata.etl.cinder.service;

import java.util.HashMap;
import java.util.Map;

import kafka.consumer.ConsumerConfig;

import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.cinder.connector.ConsumerConnectorHolder;
import xdata.etl.cinder.connector.KafkaTopicFixedModelVersionConsumerConnectorHolder;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicStatus;

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
		KafkaTopic topic = topicSetting.getTopic();
		if (topic.getStatus().equals(KafkaTopicStatus.DISABLED)) {
			return false;
		}
		if (topic instanceof KafkaTopicFixedModelVersion) {
			ConsumerConnectorHolder connectorHolder = new KafkaTopicFixedModelVersionConsumerConnectorHolder(
					kafkaClientConfig, topicSetting, transformerManager);
			connectors.put(topicSetting, connectorHolder);
			connectorHolder.run();
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean stopConsumer(
			KafkaWatchDogTopicSetting topicSetting) {
		if (!connectors.containsKey(topicSetting)) {
			return false;
		}
		ConsumerConnectorHolder connectorHolder = connectors.get(topicSetting);
		connectorHolder.shutdown();
		connectors.remove(topicSetting);
		return true;
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
