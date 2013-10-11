/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.rmi;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;

/**
 * 
 * WatchDog 接收的RMI
 * 
 * @author XuehuiHe
 * @date 2013年9月26日
 */
public interface WatchDogRMI {
	/**
	 * LogModelVersion发生了改变
	 * 
	 * @param version
	 */
	public void logModelVersionChanged(LogModelVersion<?> version);

	/**
	 * KafkaTopic 发生了改变
	 * 
	 * @param topic
	 */
	public void topicChanged(KafkaTopic topic);

	/**
	 * 启动某个kafka主题的监听
	 * 
	 * @param topic
	 */
	public void startTopic(KafkaTopic topic);

	/**
	 * 停止某个kafka主题的监听
	 * 
	 * @param topic
	 */
	public void stopTopic(KafkaTopic topic);

	/**
	 * 重启某个kafka主题的监听
	 * 
	 * @param topic
	 */
	public void restartTopic(KafkaTopic topic);
}
