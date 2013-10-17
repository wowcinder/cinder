/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.rmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.logmodelmeta.shared.rmi.WatchDogRMI;
import xdata.etl.cinder.persistence.kafka.consumer.KafkaConsumerManager;
import xdata.etl.cinder.persistence.kafka.transformer.LogModelTransformerManager;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Service("watchdog_rmi_service")
public class WatchDogRMIService implements WatchDogRMI {
	@Autowired
	private KafkaConsumerManager kafkaConsumerManager;
	@Autowired
	private LogModelTransformerManager transformerManager;

	@Override
	public void restart() {
		new Thread() {
			@Override
			public void run() {
				try {
					if (kafkaConsumerManager.shutdown()) {
						transformerManager.clear();
						kafkaConsumerManager.run();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void start() {
		new Thread() {
			public void run() {
				kafkaConsumerManager.run();
			};
		}.start();
	}

	@Override
	public void stop() {
		new Thread() {
			public void run() {
				try {
					if (kafkaConsumerManager.shutdown()) {
						transformerManager.clear();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();

	}

}
