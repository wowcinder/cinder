package xdata.etl.cinder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kafka.consumer.ConsumerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.connector.ConsumerConnectorHolder;
import xdata.etl.cinder.connector.KafkaTopicFixedModelVersionConsumerConnectorHolder;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicStatus;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus;

@Service
public class KafkaConsumerManagerImpl implements KafkaConsumerManager {
	protected static final Logger LOGGER = LoggerFactory
			.getLogger(KafkaConsumerManagerImpl.class);
	@Autowired
	private ConsumerConfig kafkaClientConfig;
	@Autowired
	private LogModelTransformerManager transformerManager;
	@Autowired
	private KafkaWatchDog dog;
	@Autowired
	private KafkaDbService dbService;

	private final Map<KafkaWatchDogTopicSetting, ConsumerConnectorHolder> connectors;

	public KafkaConsumerManagerImpl() {
		this.connectors = new HashMap<KafkaWatchDogTopicSetting, ConsumerConnectorHolder>();
	}

	@Override
	public synchronized boolean startConsumer(
			KafkaWatchDogTopicSetting topicSetting) {

		LOGGER.info("consumer start,topic:" + topicSetting.getTopic().getName());

		if (connectors.containsKey(topicSetting)) {
			ConsumerConnectorHolder holder = connectors.get(topicSetting);
			LOGGER.debug("consumer is exist,topic:"
					+ topicSetting.getTopic().getName());
			if (!holder.isShutdown()) {
				LOGGER.debug("consumer is exist and is running,topic:"
						+ topicSetting.getTopic().getName());
				return true;
			} else {
				LOGGER.debug("consumer is exist and is shutdowned,topic:"
						+ topicSetting.getTopic().getName());
				return false;
			}
		}
		KafkaTopic topic = topicSetting.getTopic();
		if (topic.getStatus().equals(KafkaTopicStatus.DISABLED)) {
			return false;
		}
		try {
			if (topic instanceof KafkaTopicFixedModelVersion) {
				LOGGER.info("FixedModelVersion consumer create,topic:"
						+ topicSetting.getTopic().getName());
				ConsumerConnectorHolder connectorHolder = new KafkaTopicFixedModelVersionConsumerConnectorHolder(
						kafkaClientConfig, topicSetting, transformerManager);
				connectors.put(topicSetting, connectorHolder);
				connectorHolder.run();
				return true;
			} else {
				// TODO
			}
		} catch (Exception e) {
			String msg = "topic:" + topicSetting.getTopic().getName()
					+ ",create consumer failed,msg:" + e.getMessage();
			LOGGER.warn(msg);
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public synchronized boolean stopConsumer(
			KafkaWatchDogTopicSetting topicSetting) {
		LOGGER.info("consumer stop,topic:" + topicSetting.getTopic().getName());
		if (!connectors.containsKey(topicSetting)) {
			return false;
		}
		ConsumerConnectorHolder connectorHolder = connectors.get(topicSetting);
		connectorHolder.shutdown();
		connectors.remove(topicSetting);
		return true;
	}

	@Override
	public synchronized boolean restartConsumer(
			KafkaWatchDogTopicSetting topicSetting) {
		LOGGER.info("consumer restart,topic:"
				+ topicSetting.getTopic().getName());
		if (stopConsumer(topicSetting)) {
			return startConsumer(topicSetting);
		}
		return false;
	}

	public ConsumerConfig getKafkaClientConfig() {
		return kafkaClientConfig;
	}

	public Map<KafkaWatchDogTopicSetting, ConsumerConnectorHolder> getConnectors() {
		return connectors;
	}

	@Override
	public synchronized void startAllConsumer() {
		List<KafkaWatchDogTopicSetting> settings = dbService
				.getAllTopicSettings(dog);
		for (KafkaWatchDogTopicSetting kafkaWatchDogTopicSetting : settings) {
			startConsumer(kafkaWatchDogTopicSetting);
		}
	}

	@Override
	public synchronized void shutdown() {
		for (Map.Entry<KafkaWatchDogTopicSetting, ConsumerConnectorHolder> entry : connectors
				.entrySet()) {
			Thread thread = new ShutDownThread(entry.getValue());
			thread.start();
		}
	}

	public static class ShutDownThread extends Thread {
		private final ConsumerConnectorHolder holder;

		public ShutDownThread(ConsumerConnectorHolder holder) {
			this.holder = holder;
		}

		@Override
		public void run() {
			holder.shutdown();
		}
	}

	@Override
	public synchronized void refreshTopicStatus() {
		List<KafkaWatchDogTopicSetting> settings = dbService
				.getAllTopicSettings(dog);
		for (KafkaWatchDogTopicSetting kafkaWatchDogTopicSetting : settings) {
			kafkaWatchDogTopicSetting
					.setStatus(KafkaWatchDogTopicSettingStatus.STOPED);
			if (connectors.containsKey(kafkaWatchDogTopicSetting)) {
				ConsumerConnectorHolder holder = connectors
						.get(kafkaWatchDogTopicSetting);
				if (!holder.isShutdown()) {
					kafkaWatchDogTopicSetting
							.setStatus(KafkaWatchDogTopicSettingStatus.RUNNING);
				}
			}
			dbService.updateWatchDogTopicSetting(kafkaWatchDogTopicSetting);
		}
	}
}
