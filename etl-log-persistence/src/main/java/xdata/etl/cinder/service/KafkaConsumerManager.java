package xdata.etl.cinder.service;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public interface KafkaConsumerManager {
	public boolean createConsumer(KafkaWatchDogTopicSetting topicSetting);
}
