/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.cinder.kafka.streamholder.KafkaStreamHolderFactory;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

/**
 * @author XuehuiHe
 * @date 2013年10月16日
 */
public class KafkaConsumerManager2 {

	private static Logger logger = LoggerFactory
			.getLogger(KafkaConsumerManager2.class);

	@Resource(name = "ottKafkaStreamHolderFactory")
	private KafkaStreamHolderFactory holderFactory;

	@Autowired
	private ConsumerConfig kafkaClientConfig;
	@Autowired
	private KafkaWatchDog dog;
	@Autowired
	private KafkaDbService dbService;
	@Autowired
	private WatchDogManagerClient client;

	private ConsumerConnector connector;
	private ExecutorService executor;
	private int threadTotal = 0;

	public void run() {

		this.connector = kafka.consumer.Consumer
				.createJavaConsumerConnector(kafkaClientConfig);

		List<KafkaWatchDogTopicSetting> settings = dbService
				.getAllTopicSettings(dog);

		Map<String, KafkaTopic> topicMap = new HashMap<String, KafkaTopic>();
		for (KafkaWatchDogTopicSetting setting : settings) {
			topicMap.put(setting.getTopic().getName(), setting.getTopic());
		}

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		for (KafkaWatchDogTopicSetting setting : settings) {
			topicCountMap.put(setting.getTopic().getName(),
					setting.getThreadNum());
			threadTotal += setting.getThreadNum();
		}

		// init connector
		Map<String, List<KafkaStream<Message>>> consumerMap = connector
				.createMessageStreams(topicCountMap);
		executor = Executors.newFixedThreadPool(threadTotal);

		for (Entry<String, List<KafkaStream<Message>>> entry : consumerMap
				.entrySet()) {
			String topic = entry.getKey();
			KafkaTopic TopicObj = topicMap.get(topic);
			List<KafkaStream<Message>> streams = entry.getValue();
			for (KafkaStream<Message> stream : streams) {
				executor.submit(holderFactory.createHolder(stream,
						TopicObj));
			}
		}
	}

	public void shutdown() throws InterruptedException {
		logger.info("正在关闭consuner...");
		if (connector != null) {
			logger.info("正在关闭consuner connector");
			connector.shutdown();
			connector = null;
		}
		if (executor != null && !executor.isShutdown()) {
			executor.shutdown();
			while (!executor.isTerminated()) {
				logger.info("正在等待处理进程shutdown,sleep 1 second");
				TimeUnit.SECONDS.sleep(1);
			}
		}
	}
}
