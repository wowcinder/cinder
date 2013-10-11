package xdata.etl.cinder.service;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public interface KafkaConsumerManager {
	public boolean startConsumer(KafkaWatchDogTopicSetting topicSetting);

	public boolean stopConsumer(KafkaWatchDogTopicSetting topicSetting);

	public boolean restartConsumer(KafkaWatchDogTopicSetting topicSetting);

	public void startAllConsumer();

	public void shutdown();
	
	public void reportTopicStatus();
}
