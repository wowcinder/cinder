/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service.kafka.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.dao.kafka.KafkaDao;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
@Transactional(readOnly = true)
@Service
public class KafkaTransactionDaoImpl implements KafkaTransactionDao {
	@Autowired
	private KafkaDao kafkaDao;

	@Override
	public Integer queryWatchDogIdByIp(String ip) {
		return kafkaDao.queryWatchDogIdByIp(ip);
	}

	@Override
	public KafkaWatchDog getDogById(Integer id) {
		return kafkaDao.getDogById(id);
	}
}
