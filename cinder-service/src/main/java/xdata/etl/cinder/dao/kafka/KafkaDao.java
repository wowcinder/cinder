/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.dao.kafka;

import java.util.List;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface KafkaDao {
	public List<Integer> getAllWatchDogIds();

	public List<Integer> getAllTopicSettingIds();

	/**
	 * @param ip
	 * @return
	 */
	public Integer queryWatchDogIdByIp(String ip);

	/**
	 * @param dogId
	 * @param rmiPort
	 */
	public void updateRmiPort(Integer dogId, Integer rmiPort);
	
	public List<KafkaTopic> getRemainKafkaTopics(Integer dogId);

	/**
	 * @param id
	 * @return
	 */
	public KafkaWatchDog getDogById(Integer id);
}
