/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.util.List;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

/**
 * @author XuehuiHe
 * @date 2013年9月25日
 */
public interface KafkaDbService {
	LogModelVersion<?> getLogModelVersion(String model, String version);

	KafkaWatchDogTopicSetting getTopicSetting(KafkaTopic topic,
			KafkaWatchDog watchDog);

	List<KafkaWatchDogTopicSetting> getRelatedTopicSettings(
			LogModelVersion<?> version);
}
