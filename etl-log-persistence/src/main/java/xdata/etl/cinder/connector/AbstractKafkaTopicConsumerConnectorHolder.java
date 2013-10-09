/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.connector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.cinder.service.LogModelTransformerManager;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public abstract class AbstractKafkaTopicConsumerConnectorHolder implements
		ConsumerConnectorHolder {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractKafkaTopicConsumerConnectorHolder.class);

	private final LogModelTransformerManager transformerManager;
	private final ConsumerConfig kafkaClientConfig;
	private final KafkaWatchDogTopicSetting topicSetting;
	private boolean isShutdown;

	private ConsumerConnector connector;
	private ExecutorService executor;

	public AbstractKafkaTopicConsumerConnectorHolder(
			ConsumerConfig kafkaClientConfig,
			KafkaWatchDogTopicSetting topicSetting,
			LogModelTransformerManager transformerManager) {
		this.topicSetting = topicSetting;
		this.kafkaClientConfig = kafkaClientConfig;
		this.transformerManager = transformerManager;
		this.isShutdown = true;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("topic: " + topicSetting.getTopic().getName()
					+ " holder be created");
		}
	}

	@Override
	public boolean isShutdown() {
		return this.isShutdown;
	}

	@Override
	public synchronized void run() {
		if (!isShutdown()) {
			return;
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("topic: " + topicSetting.getTopic().getName()
					+ " holder running");
		}
		isShutdown = false;
		this.connector = kafka.consumer.Consumer
				.createJavaConsumerConnector(kafkaClientConfig);
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topicSetting.getTopic().getName(),
				topicSetting.getThreadNum());
		Map<String, List<KafkaStream<Message>>> consumerMap = connector
				.createMessageStreams(topicCountMap);
		executor = Executors.newFixedThreadPool(topicSetting.getThreadNum());
		List<KafkaStream<Message>> streams = consumerMap.get(topicSetting
				.getTopic().getName());
		for (KafkaStream<Message> stream : streams) {
			executor.submit(new HolderRunnable(stream));
		}
	}

	protected abstract void deal(KafkaStream<Message> stream);

	@Override
	public synchronized void shutdown() {
		if (isShutdown()) {
			return;
		}
		LOGGER.info("topic: " + topicSetting.getTopic().getName()
				+ " holder start to shutdown");
		if (connector != null) {
			connector.shutdown();
			connector = null;
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (executor != null && !executor.isShutdown()) {
			executor.shutdown();
			executor = null;
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isShutdown = true;
		LOGGER.info("topic: " + topicSetting.getTopic().getName()
				+ " holder has shutdowned");
	}

	public class HolderRunnable implements Runnable {

		private final KafkaStream<Message> stream;

		public HolderRunnable(KafkaStream<Message> stream) {
			this.stream = stream;
		}

		@Override
		public void run() {
			deal(stream);
		}

	}

	public LogModelTransformerManager getTransformerManager() {
		return transformerManager;
	}

	public ConsumerConfig getKafkaClientConfig() {
		return kafkaClientConfig;
	}

	public KafkaWatchDogTopicSetting getTopicSetting() {
		return topicSetting;
	}

	public ConsumerConnector getConnector() {
		return connector;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

}
