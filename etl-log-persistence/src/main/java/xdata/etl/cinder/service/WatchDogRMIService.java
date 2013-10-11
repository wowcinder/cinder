/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.cinder.logmodelmeta.shared.rmi.WatchDogRMI;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Service("watchdog_rmi_service")
public class WatchDogRMIService implements WatchDogRMI {
	@Autowired
	private KafkaWatchDog watchDog;
	@Autowired
	private KafkaDbService dbService;
	@Autowired
	private KafkaConsumerManager consumerManager;

	public WatchDogRMIService() {
	}

	@Override
	public void logModelVersionChanged(LogModelVersion<?> version) {
		List<KafkaWatchDogTopicSetting> settings = dbService
				.getRelatedTopicSettings(version);
		for (KafkaWatchDogTopicSetting kafkaWatchDogTopicSetting : settings) {
			consumerManager.restartConsumer(kafkaWatchDogTopicSetting);
		}
	}

	@Override
	public void topicChanged(KafkaTopic topic) {
		restartTopic(topic);
	}

	@Override
	public void startTopic(KafkaTopic topic) {
		consumerManager.startConsumer(getTopicSetting(topic));
	}

	@Override
	public void stopTopic(KafkaTopic topic) {
		consumerManager.stopConsumer(getTopicSetting(topic));
	}

	@Override
	public void restartTopic(KafkaTopic topic) {
		KafkaWatchDogTopicSetting setting = getTopicSetting(topic);
		if (consumerManager.stopConsumer(setting)) {
			consumerManager.startConsumer(setting);
		}
	}

	public KafkaWatchDog getWatchDog() {
		return watchDog;
	}

	protected KafkaWatchDogTopicSetting getTopicSetting(KafkaTopic topic) {
		return dbService.getTopicSetting(topic, watchDog);
	}

}
