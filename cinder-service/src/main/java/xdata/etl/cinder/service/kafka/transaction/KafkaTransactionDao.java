/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka.transaction;


/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public interface KafkaTransactionDao {
	public Integer queryWatchDogIdByIp(String ip);
}
