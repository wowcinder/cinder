/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka;

import java.util.Map;
import java.util.Set;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface KafkaTopicSettingStatusManager {
	public void removeKafkaWatchDogTopicSetting(Integer settingId);

	public void addKafkaWatchDogTopicSetting(Integer settingId);

	public void reportAliveTopic(Set<Integer> ids);

	public void refreshTopicStatus();

	public Map<Integer, KafkaWatchDogTopicSettingStatus> getTopicSettingsStatus();
}
