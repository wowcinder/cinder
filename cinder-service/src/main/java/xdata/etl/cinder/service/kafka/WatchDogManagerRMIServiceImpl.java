/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.dao.kafka.KafkaDao;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
@Service
public class WatchDogManagerRMIServiceImpl implements WatchDogManagerRMIService {
	@Autowired
	private KafkaDao kafkaDao;
	@Autowired
	private KafkaStatusManager kafkaStatusManager;

	public WatchDogManagerRMIServiceImpl() {
	}

	@Override
	@Transactional
	public Integer login(String clientIp, Integer rmiPort) {
		Integer dogId = getWatchDogIdByIp(clientIp);
		kafkaStatusManager.login(dogId);
		kafkaDao.updateRmiPort(dogId, rmiPort);
		return dogId;
	}

	@Override
	public void logoff(String clientIp) {
		Integer dogId = getWatchDogIdByIp(clientIp);
		kafkaStatusManager.logoff(dogId);
	}

	@Override
	public void tick(String clientIp) {
		Integer dogId = getWatchDogIdByIp(clientIp);
		kafkaStatusManager.tick(dogId);
	}

	@Override
	public void reportTopicStatus(Set<Integer> aliveTopicIds) {
		kafkaStatusManager.reportAliveTopic(aliveTopicIds);
	}

	protected Integer getWatchDogIdByIp(String ip) {
		return kafkaStatusManager.getWatchDogIdByIp(ip);
	}

}
