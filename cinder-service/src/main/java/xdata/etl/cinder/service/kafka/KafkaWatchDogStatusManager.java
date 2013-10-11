/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka;

import java.util.Map;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog.KafkaProcessServerStatus;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface KafkaWatchDogStatusManager {
	public void addKafkaWatchDog(KafkaWatchDog dog);

	public void removeKafkaWatchDog(KafkaWatchDog dog);

	public void login(Integer dogId);

	public void logoff(Integer dogId);

	public void tick(Integer dogId);

	public void checkAliveDog();

	public Map<Integer, KafkaProcessServerStatus> getWatchDogStauts();

	public Integer getWatchDogIdByIp(String ip);
}
