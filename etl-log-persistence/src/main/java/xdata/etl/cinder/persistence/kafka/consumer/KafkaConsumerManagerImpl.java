/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.cinder.persistence.kafka.meta.KafkaMetaService;
import xdata.etl.cinder.persistence.kafka.streamholder.KafkaStreamHolderFactory;
import xdata.etl.cinder.persistence.rmi.WatchDogManagerClient;

/**
 * @author XuehuiHe
 * @date 2013年10月16日
 */
@Service
public class KafkaConsumerManagerImpl implements KafkaConsumerManager {

	private static Logger logger = LoggerFactory
			.getLogger(KafkaConsumerManagerImpl.class);

	@Resource(name = "ottKafkaStreamHolderFactory")
	private KafkaStreamHolderFactory holderFactory;

	@Autowired
	private ConsumerConfig kafkaClientConfig;
	@Autowired
	private KafkaWatchDog dog;
	@Autowired
	private KafkaMetaService metaService;
	@Autowired
	private WatchDogManagerClient client;

	private final AtomicBoolean isRunning;

	private ConsumerConnector connector;
	private ExecutorService executor;
	private int threadTotal;

	public KafkaConsumerManagerImpl() {
		isRunning = new AtomicBoolean(false);
	}

	public boolean run() {
		if (isRunning.get()) {
			logger.info("consuner已经启动");
			return false;
		}
		if (!isRunning.compareAndSet(false, true)) {
			logger.info("consuner正在启动中....");
			return false;
		}

		threadTotal = 0;
		logger.info("consuner启动.....");
		this.connector = kafka.consumer.Consumer
				.createJavaConsumerConnector(kafkaClientConfig);

		List<KafkaWatchDogTopicSetting> settings = metaService
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
				executor.submit(holderFactory.createHolder(stream, TopicObj));
			}
		}

		client.enabled();
		client.tick();
		logger.info("consuner启动完毕.....");

		return true;
	}

	public boolean shutdown() throws InterruptedException {
		if (!isRunning.get()) {
			logger.info("consuner已经关闭...");
			return false;
		}
		if (!isRunning.compareAndSet(true, false)) {
			logger.info("consuner正在关闭中...");
			return false;
		}
		client.disabled();
		client.logoff();
		logger.info("关闭consuner...");
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
		logger.info("consuner关闭完毕.....");
		return true;
	}
}
