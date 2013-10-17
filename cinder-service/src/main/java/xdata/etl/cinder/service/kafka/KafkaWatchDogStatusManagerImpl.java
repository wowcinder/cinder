/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog.KafkaProcessServerStatus;
import xdata.etl.cinder.service.kafka.transaction.KafkaTransactionDao;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
@Service("kafkaWatchDogStatusManager")
public class KafkaWatchDogStatusManagerImpl implements
		KafkaWatchDogStatusManager {
	private final Map<Integer, KafkaProcessServerStatus> dogsStatusMap;
	private final Set<Integer> aliveDogs;
	private final Set<Integer> lastTickDogs;
	@Autowired
	private KafkaTransactionDao transactionDao;
	private final Map<String, Integer> ipToId;

	public KafkaWatchDogStatusManagerImpl() {
		dogsStatusMap = new HashMap<Integer, KafkaWatchDog.KafkaProcessServerStatus>();
		aliveDogs = new HashSet<Integer>();
		ipToId = new HashMap<String, Integer>();
		lastTickDogs = new HashSet<Integer>();
	}

	@Override
	public synchronized void login(Integer dogId) {
		dogsStatusMap.put(dogId, KafkaProcessServerStatus.STARTING);
		aliveDogs.add(dogId);
	}

	@Override
	public synchronized void logoff(Integer dogId) {
		dogsStatusMap.put(dogId, KafkaProcessServerStatus.STOPED);
		aliveDogs.remove(dogId);
	}

	@Override
	public synchronized void tick(Integer dogId) {
		dogsStatusMap.put(dogId, KafkaProcessServerStatus.RUNNING);
		aliveDogs.add(dogId);
		lastTickDogs.add(dogId);
	}

	@Override
	public synchronized void checkAliveDog() {
		for (Integer dogId : aliveDogs) {
			if (!lastTickDogs.contains(dogId)) {
				if (!KafkaProcessServerStatus.STARTING.equals(dogsStatusMap
						.get(dogId))) {
					dogsStatusMap
							.put(dogId, KafkaProcessServerStatus.EXCEPTION);
				}
			}
		}
		lastTickDogs.clear();
	}

	@Override
	public Map<Integer, KafkaProcessServerStatus> getWatchDogStauts() {
		Map<Integer, KafkaProcessServerStatus> dogsStatusMap = new HashMap<Integer, KafkaWatchDog.KafkaProcessServerStatus>();
		synchronized (this.dogsStatusMap) {
			dogsStatusMap.putAll(this.dogsStatusMap);
		}
		return dogsStatusMap;
	}

	
	public void setTransactionDao(KafkaTransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	public Integer getWatchDogIdByIp(String ip) {
		if (!ipToId.containsKey(ip)) {
			queryWatchDogIdByIp(ip);
		}
		return ipToId.get(ip);
	}

	protected synchronized void queryWatchDogIdByIp(String ip) {
		if (ipToId.containsKey(ip)) {
			return;
		}
		Integer id = transactionDao.queryWatchDogIdByIp(ip);
		if (id != null) {
			ipToId.put(ip, id);
		}
	}
}
