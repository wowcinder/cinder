/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka.transaction;

import java.util.List;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface KafkaTransactionDao {
	public Integer queryWatchDogIdByIp(String ip);

	public List<Integer> getAllWatchDogIds();

	public List<Integer> getAllTopicSettingIds();
}
