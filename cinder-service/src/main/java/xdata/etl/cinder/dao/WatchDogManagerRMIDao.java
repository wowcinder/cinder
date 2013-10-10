/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.dao;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;

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
}
