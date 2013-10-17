/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka.transaction;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;


/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface KafkaTransactionDao {
	public Integer queryWatchDogIdByIp(String ip);
	public KafkaWatchDog getDogById(Integer id);
}
