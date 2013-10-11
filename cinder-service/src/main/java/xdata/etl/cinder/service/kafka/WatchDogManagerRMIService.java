/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka;

import java.util.Set;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface WatchDogManagerRMIService {

	/**
	 * @param clientIp
	 * @param rmiPort
	 * @return
	 */
	Integer login(String clientIp, Integer rmiPort);

	/**
	 * @param clientIp
	 */
	void logoff(String clientIp);

	/**
	 * @param clientIp
	 */
	void tick(String clientIp);

	/**
	 * @param clientIp
	 * @param aliveTopicIds
	 */
	void reportTopicStatus(Set<Integer> aliveTopicIds);

}
