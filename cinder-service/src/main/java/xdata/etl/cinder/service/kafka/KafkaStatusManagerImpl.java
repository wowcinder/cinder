/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog.KafkaProcessServerStatus;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
@Service
public class KafkaStatusManagerImpl implements KafkaStatusManager {
	@Resource(name = "kafkaTopicSettingStatusManager")
	private KafkaTopicSettingStatusManager topicSettingStatusManager;
	@Resource(name = "kafkaWatchDogStatusManager")
	private KafkaWatchDogStatusManager watchDogStatusManager;

	@Override
	public void removeKafkaWatchDogTopicSetting(Integer settingId) {
		topicSettingStatusManager.removeKafkaWatchDogTopicSetting(settingId);
	}

	@Override
	public void addKafkaWatchDogTopicSetting(Integer settingId) {
		topicSettingStatusManager.addKafkaWatchDogTopicSetting(settingId);
	}

	@Override
	public void reportAliveTopic(Set<Integer> ids) {
		topicSettingStatusManager.reportAliveTopic(ids);
	}

	@Override
	public void refreshTopicStatus() {
		topicSettingStatusManager.refreshTopicStatus();
	}

	@Override
	public Map<Integer, KafkaWatchDogTopicSettingStatus> getTopicSettingsStatus() {
		return topicSettingStatusManager.getTopicSettingsStatus();
	}

	@Override
	public void addKafkaWatchDog(KafkaWatchDog dog) {
		watchDogStatusManager.addKafkaWatchDog(dog);
	}

	@Override
	public void removeKafkaWatchDog(KafkaWatchDog dog) {
		watchDogStatusManager.removeKafkaWatchDog(dog);
	}

	@Override
	public void login(Integer dogId) {
		watchDogStatusManager.login(dogId);
	}

	@Override
	public void logoff(Integer dogId) {
		watchDogStatusManager.logoff(dogId);
	}

	@Override
	public void tick(Integer dogId) {
		watchDogStatusManager.tick(dogId);
	}

	@Override
	public void checkAliveDog() {
		watchDogStatusManager.checkAliveDog();
	}

	@Override
	public Map<Integer, KafkaProcessServerStatus> getWatchDogStauts() {
		return watchDogStatusManager.getWatchDogStauts();
	}

	@Override
	public void refreshStatus() {
		checkAliveDog();
		refreshTopicStatus();
	}

	@Override
	public Integer getWatchDogIdByIp(String ip) {
		return watchDogStatusManager.getWatchDogIdByIp(ip);
	}
}
