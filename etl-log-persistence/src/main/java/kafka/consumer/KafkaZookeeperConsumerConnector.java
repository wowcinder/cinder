package kafka.consumer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

import kafka.utils.KafkaScheduler;

import org.I0Itec.zkclient.ZkClient;

import scala.Option;

public class KafkaZookeeperConsumerConnector extends ZookeeperConsumerConnector {

	public KafkaZookeeperConsumerConnector(ConsumerConfig config) {
		super(config, true);
	}

	@Override
	public void shutdown() {
		boolean canShutdown = getIsShuttingDown().compareAndSet(false, true);
		if (canShutdown) {
			ZookeeperTopicEventWatcher wildcardTopicWatcher = getWildcardTopicWatcher();
			if (wildcardTopicWatcher != null)
				wildcardTopicWatcher.shutdown();
			try {
				KafkaScheduler scheduler = getScheduler();
				scheduler.shutdownNow();
				Option<Fetcher> fetcher = getFetcher();
				if (fetcher.get() != null) {
					fetcher.get().stopConnectionsToAllBrokers();
				}
				sendShutdownToAllQueues2();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.shutdown();
	}

	public ZkClient getZkClient() {
		Field field;
		try {
			field = ZookeeperConsumerConnector.class
					.getDeclaredField("kafka$consumer$ZookeeperConsumerConnector$$zkClient");
			field.setAccessible(true);
			return (ZkClient) field.get(this);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void sendShutdownToAllQueues2() {
		Method method;
		try {
			method = ZookeeperConsumerConnector.class
					.getDeclaredMethod("sendShutdownToAllQueues");
			method.setAccessible(true);
			method.invoke(this);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	protected Option<Fetcher> getFetcher() {
		Field field;
		try {
			field = ZookeeperConsumerConnector.class
					.getDeclaredField("kafka$consumer$ZookeeperConsumerConnector$$fetcher");
			field.setAccessible(true);
			return (Option<Fetcher>) field.get(this);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected AtomicBoolean getIsShuttingDown() {
		Field field;
		try {
			field = ZookeeperConsumerConnector.class
					.getDeclaredField("kafka$consumer$ZookeeperConsumerConnector$$isShuttingDown");
			field.setAccessible(true);
			return (AtomicBoolean) field.get(this);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected KafkaScheduler getScheduler() {
		Field field;
		try {
			field = ZookeeperConsumerConnector.class
					.getDeclaredField("scheduler");
			field.setAccessible(true);
			return (KafkaScheduler) field.get(this);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected ZookeeperTopicEventWatcher getWildcardTopicWatcher() {
		Field field;
		try {
			field = ZookeeperConsumerConnector.class
					.getDeclaredField("kafka$consumer$ZookeeperConsumerConnector$$wildcardTopicWatcher");
			field.setAccessible(true);
			return (ZookeeperTopicEventWatcher) field.get(this);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
