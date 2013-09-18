package xdata.etl.kafka;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PreDestroy;

import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;

import org.springframework.core.io.Resource;

import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.process.cache.TopicProcessCache;
import xdata.etl.kafka.topic.KafkaTopicManager;
import xdata.etl.kafka.transform.KafkaTransformer;
import xdata.etl.kafka.transform.cache.TransformerCache;
import xdata.etl.kafka.transform.result.KafkaTransformResult;
import xdata.etl.util.ClassScaner;
import xdata.etl.util.ClassScaner.KafkaTopicClassFilter;

public class KafkaContext {
	private TransformerCache transformerCache;
	private TopicProcessCache topicProcessCache;
	private KafkaTopicManager topicManager;

	private ConsumerConfig kafkaClientConfig;

	private String cdnCacheSnapFolderPath;

	private int cdnCacheSnapProcessThread;

	public KafkaContext(Resource configFile, ClassScaner entityClassScaner,
			TransformerCache transformerCache,
			TopicProcessCache topicProcessCache) throws IOException {
		if (configFile.exists()) {
			Properties props = new Properties();
			props.load(configFile.getInputStream());
			kafkaClientConfig = new ConsumerConfig(props);
		} else {
			throw new FileNotFoundException("ConsumerConfig FILE NOT FOUND!");
		}
		this.transformerCache = transformerCache;
		setTopicProcessCache(topicProcessCache);

		topicManager = new KafkaTopicManager();

		KafkaTopicClassFilter filter = new KafkaTopicClassFilter();
		for (Class<? extends HbaseEntity> clazz : filter
				.filte(entityClassScaner)) {
			analyze(clazz);
		}
	}

	public KafkaTransformResult transform(String topic, String raw)
			throws KafkaTransformException {
		Class<? extends HbaseEntity> entityClass = topicManager.getClazz(topic);
		KafkaTransformer transformer = transformerCache.getTransformer(topic);
		if (transformer != null) {
			return transformer.transform(entityClass, raw);
		}
		return null;
	}

	private void analyze(Class<? extends HbaseEntity> clazz) {
		transformerCache.analyze(clazz);

		topicProcessCache
				.analyze(clazz, transformerCache.getTransformer(clazz));

		topicManager.analyze(clazz);
	}

	public TransformerCache getTransformerCache() {
		return transformerCache;
	}

	public synchronized ConsumerConnector getKafkaConsumer() {
		return kafka.consumer.Consumer
				.createJavaConsumerConnector(kafkaClientConfig);
	}

	public ConsumerConfig getKafkaClientConfig() {
		return kafkaClientConfig;
	}

	public KafkaTopicManager getTopicManager() {
		return topicManager;
	}

	public TopicProcessCache getTopicProcessCache() {
		return topicProcessCache;
	}

	public void setTopicProcessCache(TopicProcessCache topicProcessCache) {
		this.topicProcessCache = topicProcessCache;
		this.topicProcessCache.setKafkaCxt(this);
	}

	@PreDestroy
	public void close() {
		topicProcessCache.shutdown();
	}

	public String getCdnCacheSnapFolderPath() {
		return cdnCacheSnapFolderPath;
	}

	public void setCdnCacheSnapFolderPath(String cdnCacheSnapFolderPath) {
		this.cdnCacheSnapFolderPath = cdnCacheSnapFolderPath;
	}

	public int getCdnCacheSnapProcessThread() {
		return cdnCacheSnapProcessThread;
	}

	public void setCdnCacheSnapProcessThread(int cdnCacheSnapProcessThread) {
		this.cdnCacheSnapProcessThread = cdnCacheSnapProcessThread;
	}

}
