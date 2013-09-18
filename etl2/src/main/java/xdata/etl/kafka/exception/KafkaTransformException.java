package xdata.etl.kafka.exception;

import xdata.etl.hbase.entity.HbaseEntity;

public class KafkaTransformException extends Exception {
	private static final long serialVersionUID = -8072554907402191411L;
	private Class<? extends HbaseEntity> clazz;
	private String raw;

	public KafkaTransformException(Class<? extends HbaseEntity> clazz,
			String raw) {
		this.clazz = clazz;
		this.raw = raw;
	}

	public KafkaTransformException(Class<? extends HbaseEntity> clazz,
			String raw, String message) {
		super(message);
		this.clazz = clazz;
		this.raw = raw;
	}

	public KafkaTransformException(Class<? extends HbaseEntity> clazz,
			String raw, Throwable cause) {
		super(cause);
		this.clazz = clazz;
		this.raw = raw;
	}

	public KafkaTransformException(Class<? extends HbaseEntity> clazz,
			String raw, String message, Throwable cause) {
		super(message, cause);
		this.clazz = clazz;
		this.raw = raw;
	}

	public Class<? extends HbaseEntity> getClazz() {
		return clazz;
	}

	public String getRaw() {
		return raw;
	}

	public void setClazz(Class<? extends HbaseEntity> clazz) {
		this.clazz = clazz;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

}
