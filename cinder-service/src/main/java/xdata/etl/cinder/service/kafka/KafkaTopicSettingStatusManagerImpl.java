/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.dao.kafka.KafkaDao;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
@Service("kafkaTopicSettingStatusManager")
public class KafkaTopicSettingStatusManagerImpl implements
		KafkaTopicSettingStatusManager {
	private final Map<Integer, KafkaWatchDogTopicSettingStatus> topicSettingStatusMap;
	private final Set<Integer> aliveTopicSettingIds;
	private KafkaDao kafkaDao;

	public KafkaTopicSettingStatusManagerImpl() {
		topicSettingStatusMap = new ConcurrentHashMap<Integer, KafkaWatchDogTopicSettingStatus>();
		aliveTopicSettingIds = Collections
				.synchronizedSet(new HashSet<Integer>());
	}

	@Override
	public void removeKafkaWatchDogTopicSetting(Integer settingId) {
		topicSettingStatusMap.remove(settingId);
	}

	@Override
	public void addKafkaWatchDogTopicSetting(Integer settingId) {
		topicSettingStatusMap.put(settingId,
				KafkaWatchDogTopicSettingStatus.STOPED);
	}

	@Override
	public void reportAliveTopic(Set<Integer> ids) {
		aliveTopicSettingIds.addAll(ids);
	}

	@Override
	public void refreshTopicStatus() {
		final Set<Integer> aliveTopicSettingIds = new HashSet<Integer>();
		synchronized (this.aliveTopicSettingIds) {
			aliveTopicSettingIds.addAll(this.aliveTopicSettingIds);
			this.aliveTopicSettingIds.clear();
		}
		for (Integer topicSettingId : topicSettingStatusMap.keySet()) {
			if (aliveTopicSettingIds.contains(topicSettingId)) {
				topicSettingStatusMap.put(topicSettingId,
						KafkaWatchDogTopicSettingStatus.RUNNING);
			} else {
				topicSettingStatusMap.put(topicSettingId,
						KafkaWatchDogTopicSettingStatus.STOPED);
			}
		}
	}

	@Override
	public Map<Integer, KafkaWatchDogTopicSettingStatus> getTopicSettingsStatus() {
		Map<Integer, KafkaWatchDogTopicSettingStatus> topicSettingStatusMap = new HashMap<Integer, KafkaWatchDogTopicSettingStatus>();
		synchronized (this.topicSettingStatusMap) {
			topicSettingStatusMap.putAll(this.topicSettingStatusMap);
		}
		return topicSettingStatusMap;
	}

	public KafkaDao getKafkaDao() {
		return kafkaDao;
	}

	@Autowired
	public void setKafkaDao(KafkaDao kafkaDao) {
		this.kafkaDao = kafkaDao;
		init();
	}
	@Transactional(readOnly = true)
	private void init() {
		List<Integer> settingIds = getKafkaDao().getAllTopicSettingIds();
		synchronized (this.topicSettingStatusMap) {
			for (Integer id : settingIds) {
				topicSettingStatusMap.put(id,
						KafkaWatchDogTopicSettingStatus.STOPED);
			}
		}
	}
}
