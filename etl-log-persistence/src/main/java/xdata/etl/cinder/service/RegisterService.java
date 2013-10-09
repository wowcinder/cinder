/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;

/**
 * @author XuehuiHe
 * @date 2013年10月9日
 */
@Configuration
public class RegisterService {
	@Autowired
	private WatchDogManagerClient client;
	@Autowired
	private KafkaDbService dbService;

	public RegisterService() {
	}

	@Bean
	public KafkaWatchDog getSelf() {
		Integer id = client.registerPrcessSever(1299);
		return dbService.findWatchDog(id);
	}
}
