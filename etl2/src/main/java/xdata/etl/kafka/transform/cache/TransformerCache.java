package xdata.etl.kafka.transform.cache;

import java.util.HashMap;
import java.util.Map;

import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.annotatins.Kafka;
import xdata.etl.kafka.transform.KafkaTransformer;

public class TransformerCache {

	private Map<Class<? extends HbaseEntity>, KafkaTransformer> entityClassToTransformer;
	private Map<String, KafkaTransformer> topicToTransformer;

	private Map<Class<? extends KafkaTransformer>, KafkaTransformer> cache;

	public TransformerCache(KafkaTransformer... transformers) {
		entityClassToTransformer = new HashMap<Class<? extends HbaseEntity>, KafkaTransformer>();
		topicToTransformer = new HashMap<String, KafkaTransformer>();

		cache = new HashMap<Class<? extends KafkaTransformer>, KafkaTransformer>();

		for (KafkaTransformer kafkaTransformer : transformers) {
			cache.put(kafkaTransformer.getClass(),
					kafkaTransformer);
		}

	}

	public KafkaTransformer getTransformer(String topic) {
		return topicToTransformer.get(topic);
	}

	public KafkaTransformer getTransformer(Class<? extends HbaseEntity> clazz) {
		return entityClassToTransformer.get(clazz);
	}

	public void analyze(Class<? extends HbaseEntity> clazz) {
		Kafka kafka = clazz.getAnnotation(Kafka.class);
		if (kafka != null) {
			String topic = kafka.topic();
			Class<? extends KafkaTransformer> transformerClass = kafka
					.transformer();
			KafkaTransformer transformer = _getTransformer(transformerClass);
			if (transformer != null) {
				entityClassToTransformer.put(clazz, transformer);
				topicToTransformer.put(topic, transformer);
			}
		}
	}

	protected KafkaTransformer _getTransformer(
			Class<? extends KafkaTransformer> transformerClass) {
		if (cache.containsKey(transformerClass)) {
			return cache.get(transformerClass);
		}
		KafkaTransformer transformer = null;
		try {
			transformer = transformerClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		cache.put(transformerClass, transformer);
		return transformer;
	}
}
