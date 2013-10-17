/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.kafka.consumer;

import java.lang.reflect.Field;
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
import kafka.javaapi.consumer.MyZookeeperConsumerConnector;
import kafka.message.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.Unsafe;
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
@SuppressWarnings("restriction")
@Service("kafkaConsumerManager")
public class KafkaConsumerManagerImpl implements KafkaConsumerManager {

	private static Unsafe unsafe = null;
	static {
		try {
			Class<?> clazz = Unsafe.class;
			Field f;
			f = clazz.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			unsafe = (Unsafe) f.get(clazz);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	private static final long valueOffset;

	static {
		try {
			valueOffset = unsafe
					.objectFieldOffset(KafkaConsumerManagerImpl.class
							.getDeclaredField("status"));
		} catch (Exception ex) {
			throw new Error(ex);
		}
	}

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

	private volatile ConsumerStatus status;

//	private ConsumerConnector connector;
	private MyZookeeperConsumerConnector connector;
	
	private ExecutorService executor;
	private volatile int threadTotal;

	public KafkaConsumerManagerImpl() {
		status = ConsumerStatus.STOPED;
	}

	public boolean run() {
		if (!compareAndSet(ConsumerStatus.STOPED, ConsumerStatus.STARTING)) {
			logger.info("consuner正处在" + status.name() + ",无法启动");
			return false;
		}
		threadTotal = 0;
		logger.info("consuner启动.....");
//		this.connector = kafka.consumer.Consumer
//				.createJavaConsumerConnector(kafkaClientConfig);
		this.connector = new MyZookeeperConsumerConnector(kafkaClientConfig);
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
		if (threadTotal > 0) {
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
					executor.submit(holderFactory
							.createHolder(stream, TopicObj));
				}
			}
		}

		client.enabled();
		client.tick();
		logger.info("consuner启动完毕.....");
		status = ConsumerStatus.RUNNING;

		return true;
	}

	public boolean shutdown() throws InterruptedException {
		if (!compareAndSet(ConsumerStatus.RUNNING, ConsumerStatus.STOPPING)) {
			logger.info("consuner正处在" + status.name() + ",无法关闭");
			return false;
		}
		client.disabled();
		client.logoff();
		logger.info("关闭consuner...");
		if (connector != null) {
			logger.info("正在关闭consuner connector");
			connector.shutdown();
		}
		if (executor != null && !executor.isShutdown()) {
			executor.shutdown();
			while (!executor.isTerminated()) {
				logger.info("正在等待处理进程shutdown,sleep 1 second");
				TimeUnit.SECONDS.sleep(1);
			}
		}
		if (connector != null) {
			logger.info("正在commitOffsets...shutdownZkClient");
			connector.commitOffsets();
			connector.shutdownZkClient();
			connector = null;
		}
		logger.info("consuner关闭完毕.....");
		status = ConsumerStatus.STOPED;
		return true;
	}

	public final boolean compareAndSet(ConsumerStatus expect,
			ConsumerStatus update) {
		return unsafe.compareAndSwapObject(this, valueOffset, expect, update);
	}

	public enum ConsumerStatus {
		STARTING, RUNNING, STOPPING, STOPED, COMMITOFFSETSING;
	}

	@Override
	public void commitOffsets() {
		if (connector != null && threadTotal > 0) {
			if (compareAndSet(ConsumerStatus.RUNNING,
					ConsumerStatus.COMMITOFFSETSING)) {
				logger.info("commitOffsets.....");
				connector.commitOffsets();
				logger.info("commitOffsets end");
				status = ConsumerStatus.RUNNING;
			}
		}
	}
}
