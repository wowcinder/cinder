package xdata.etl.kafka.exception;

import java.lang.reflect.Field;

import xdata.etl.hbase.entity.HbaseEntity;

public class TerminalFieldValueParseException extends KafkaTransformException {
	private static final long serialVersionUID = -1776127166591836800L;
	private Field targetField;
	private String fieldStr;

	public TerminalFieldValueParseException(Class<? extends HbaseEntity> clazz,
			String raw) {
		super(clazz, raw);
	}

	public Field getTargetField() {
		return targetField;
	}

	public String getFieldStr() {
		return fieldStr;
	}

	public void setTargetField(Field targetField) {
		this.targetField = targetField;
	}

	public void setFieldStr(String fieldStr) {
		this.fieldStr = fieldStr;
	}

}
