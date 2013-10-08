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
