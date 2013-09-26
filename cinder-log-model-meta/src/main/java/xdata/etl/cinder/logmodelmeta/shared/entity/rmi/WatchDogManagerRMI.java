/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.rmi;

import java.util.List;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

/**
 * 服务端接收的RMI
 * 
 * @author XuehuiHe
 * @date 2013年9月26日
 */
public interface WatchDogManagerRMI {
	/**
	 * 注册
	 * 
	 * @param ip
	 * @return 注册成功或失败
	 */
	public Boolean registerPrcessSever(String ip);

	/**
	 * 发送心跳
	 */
	public void tick();

	/**
	 * 汇报topic的状态
	 * 
	 * @param topicStatus
	 */
	public void reportTopicStatus(List<KafkaWatchDogTopicSetting> topicStatus);
}
