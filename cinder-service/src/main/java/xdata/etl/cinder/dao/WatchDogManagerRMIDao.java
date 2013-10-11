/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.dao;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public interface WatchDogManagerRMIDao {
	KafkaWatchDog findWatchDog(String ip, Integer rmiPort);

	void refreshWatchDogAliveTime(String ip);

	/**
	 * @param clientIp
	 */
	void logoff(String clientIp);

	/**
	 * @param clientIp
	 * @param topicToStatus
	 */
	void refreshTopicStatus(String clientIp,
			Map<Integer, KafkaWatchDogTopicSettingStatus> topicToStatus);

	/**
	 * @param liveIps
	 * @param lastTickMap
	 * @param topicStatus
	 */
	void refreshWatchDogStatus(
			Set<String> liveIps,
			Map<String, Date> lastTickMap,
			Map<String, Map<Integer, KafkaWatchDogTopicSettingStatus>> topicStatus);
}
