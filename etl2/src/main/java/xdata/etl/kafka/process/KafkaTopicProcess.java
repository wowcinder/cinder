package xdata.etl.kafka.process;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.KafkaContext;
import xdata.etl.kafka.exception.KafkaTopicProcessException;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.transform.KafkaTransformer;

public abstract class KafkaTopicProcess {

	private HbaseContext hbaseCtx;

	private KafkaContext kafkaCtx;

	private String topic;
	private KafkaTransformer transformer;
	private Class<? extends HbaseEntity> clazz;

	public KafkaTopicProcess() {
	}

	public abstract void process(String raw) throws KafkaTopicProcessException,
			KafkaTransformException;

	public KafkaContext getKafkaCtx() {
		return kafkaCtx;
	}

	public void setKafkaCtx(KafkaContext kafkaCtx) {
		this.kafkaCtx = kafkaCtx;
	}

	public HbaseContext getHbaseCtx() {
		return hbaseCtx;
	}

	public void setHbaseCtx(HbaseContext hbaseCtx) {
		this.hbaseCtx = hbaseCtx;
	}

	public String getTopic() {
		return topic;
	}

	public KafkaTransformer getTransformer() {
		return transformer;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setTransformer(KafkaTransformer transformer) {
		this.transformer = transformer;
	}

	public Class<? extends HbaseEntity> getClazz() {
		return clazz;
	}

	public void setClazz(Class<? extends HbaseEntity> clazz) {
		this.clazz = clazz;
	}

	public void shutdown() {

	}

}
