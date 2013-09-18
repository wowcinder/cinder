package xdata.etl.hbase.column;

import java.lang.reflect.Field;

import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.entity.HbaseEntity;

public class HbaseColumnInfo {
	private Field field;
	private String columnName;
	private Class<? extends HbaseEntity> owner;

	private boolean isEmbeddable = false;
	private Class<?> embeddClass;
	private Field embeddFiled;

	public HbaseColumnInfo(Class<? extends HbaseEntity> owner) {
		this.owner = owner;
	}

	public Field getField() {
		return field;
	}

	public boolean isEmbeddable() {
		return isEmbeddable;
	}

	public Class<?> getEmbeddClass() {
		return embeddClass;
	}

	public String getColumnName() {
		return columnName;
	}

	public Class<? extends HbaseEntity> getOwner() {
		return owner;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public void setEmbeddable(boolean isEmbeddable) {
		this.isEmbeddable = isEmbeddable;
	}

	public Field getEmbeddFiled() {
		return embeddFiled;
	}

	public void setEmbeddFiled(Field embeddFiled) {
		this.embeddFiled = embeddFiled;
	}

	public void setEmbeddClass(Class<?> embeddClass) {
		if (embeddClass == null) {
			setEmbeddable(false);
		} else {
			setEmbeddable(true);
		}
		this.embeddClass = embeddClass;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setOwner(Class<? extends HbaseEntity> owner) {
		this.owner = owner;
	}

	public static HbaseColumnInfo create(Class<? extends HbaseEntity> clazz,
			Field field) {
		HbaseColumnInfo columnInfo = new HbaseColumnInfo(clazz);
		Class<?> embedded = getEmbeddableClass(field);
		if (embedded == null) {
			columnInfo.setColumnName(getColumnName(field));
			columnInfo.setField(field);
		}
		return null;
	}

	private static String getColumnName(Field field) {
		HbaseColumn column = field.getAnnotation(HbaseColumn.class);
		if (column == null || column.name() == null || column.name().equals("")) {
			return field.getName();
		}
		return column.name();
	}

	private static Class<?> getEmbeddableClass(Field field) {
		HbaseEmbedded embedded = field.getAnnotation(HbaseEmbedded.class);
		if (embedded != null) {
			return field.getType();
		}
		return null;
	}

}
