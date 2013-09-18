package xdata.etl.kafka.process.cache;

import java.util.HashMap;

import javax.annotation.Resource;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.KafkaContext;
import xdata.etl.kafka.annotatins.Kafka;
import xdata.etl.kafka.process.KafkaTopicProcess;
import xdata.etl.kafka.transform.KafkaTransformer;

public class TopicProcessCache extends HashMap<String, KafkaTopicProcess> {
	private static final long serialVersionUID = -4179285472969858957L;

	@Resource
	private HbaseContext hbaseCxt;
	private KafkaContext kafkaCxt;

	public TopicProcessCache() {
		super();
	}

	public TopicProcessCache(KafkaTopicProcess... processes) {
		this();
		for (KafkaTopicProcess process : processes) {
			put(process.getTopic(), process);
		}
	}

	public void shutdown() {
		for (String topic : keySet()) {
			KafkaTopicProcess process = get(topic);
			if (process != null) {
				process.shutdown();
			}
		}

		hbaseCxt = null;
		kafkaCxt = null;
	}

	public void analyze(Class<? extends HbaseEntity> clazz,
			KafkaTransformer transformer) {
		Kafka kafka = clazz.getAnnotation(Kafka.class);
		if (kafka != null) {
			String topic = kafka.topic();
			Class<? extends KafkaTopicProcess> processClass = kafka.process();
			KafkaTopicProcess process = null;
			try {
				process = processClass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			process.setTopic(topic);
			process.setClazz(clazz);
			process.setHbaseCtx(getHbaseCxt());
			process.setKafkaCtx(getKafkaCxt());
			process.setTransformer(transformer);

			put(topic, process);
		}
	}

	public HbaseContext getHbaseCxt() {
		return hbaseCxt;
	}

	public KafkaContext getKafkaCxt() {
		return kafkaCxt;
	}

	public void setHbaseCxt(HbaseContext hbaseCxt) {
		this.hbaseCxt = hbaseCxt;
	}

	public void setKafkaCxt(KafkaContext kafkaCxt) {
		this.kafkaCxt = kafkaCxt;
	}

}
