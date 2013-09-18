package xdata.etl.kafka.topic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.annotatins.Kafka;

public class KafkaTopicManager {
	private Map<Class<? extends HbaseEntity>, String> clazzToTopic;
	private Map<String, Class<? extends HbaseEntity>> topicToClazz;

	private Map<Class<? extends HbaseEntity>, String> SnapClazzToTopic;
	private Map<String, Class<? extends HbaseEntity>> SnapTopicToClazz;

	public KafkaTopicManager() {
		clazzToTopic = new HashMap<Class<? extends HbaseEntity>, String>();
		topicToClazz = new HashMap<String, Class<? extends HbaseEntity>>();

		SnapClazzToTopic = new HashMap<Class<? extends HbaseEntity>, String>();
		SnapTopicToClazz = new HashMap<String, Class<? extends HbaseEntity>>();
	}

	public void add(Class<? extends HbaseEntity> clazz, String topic) {
		clazzToTopic.put(clazz, topic);
		topicToClazz.put(topic, clazz);
	}

	public void addSnap(Class<? extends HbaseEntity> clazz, String topic) {
		SnapClazzToTopic.put(clazz, topic);
		SnapTopicToClazz.put(topic, clazz);
	}

	public Class<? extends HbaseEntity> getClazz(String topic) {
		if (topicToClazz.containsKey(topic)) {
			return topicToClazz.get(topic);
		} else {
			return SnapTopicToClazz.get(topic);
		}
	}

	public String getTopic(Class<? extends HbaseEntity> clazz) {
		if (clazzToTopic.containsKey(clazz)) {
			return clazzToTopic.get(clazz);
		} else {
			return SnapClazzToTopic.get(clazz);
		}
	}

	public Set<String> getTopics() {
		return topicToClazz.keySet();
	}

	public Set<String> getSnapTopic() {
		return SnapTopicToClazz.keySet();
	}

	public void analyze(Class<? extends HbaseEntity> clazz) {
		Kafka k = clazz.getAnnotation(Kafka.class);
		if (k != null) {
			if (k.isSnap()) {
				addSnap(clazz, k.topic());
			} else {
				add(clazz, k.topic());
			}
		}
	}

}
