/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka;


/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface KafkaStatusManager extends KafkaWatchDogStatusManager,
		KafkaTopicSettingStatusManager {
	public void refreshStatus();

}
