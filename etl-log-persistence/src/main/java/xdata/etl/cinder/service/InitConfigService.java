/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;

/**
 * @author XuehuiHe
 * @date 2013年10月9日
 */
@Configuration
public class InitConfigService {
	@Autowired
	private WatchDogManagerClient client;
	@Autowired
	private KafkaDbService dbService;
	@Autowired
	private PropertyHolder propertyHolder;

	public InitConfigService() {
	}

	@Bean(name = "hbaseConf")
	public org.apache.hadoop.conf.Configuration getHbaseConfig() {
		return HBaseConfiguration.create();
	}

	@Bean
	public KafkaWatchDog getWatchDog() {
		Integer id = client.login(propertyHolder.getRmiPort());
		return dbService.findWatchDog(id);
	}

	@Bean
	public ConsumerConfig getKafkaConfig() throws IOException {
		Resource configFile = propertyHolder.getConfigFile();
		if (configFile.exists()) {
			Properties props = new Properties();
			props.load(configFile.getInputStream());
			return new ConsumerConfig(props);
		} else {
			throw new FileNotFoundException("ConsumerConfig FILE NOT FOUND!");
		}
	}

	public static class PropertyHolder {
		private final Resource configFile;
		private final Integer rmiPort;

		public PropertyHolder(Resource configFile, Integer rmiPort) {
			this.configFile = configFile;
			this.rmiPort = rmiPort;
		}

		public Resource getConfigFile() {
			return configFile;
		}

		public Integer getRmiPort() {
			return rmiPort;
		}
	}
}
