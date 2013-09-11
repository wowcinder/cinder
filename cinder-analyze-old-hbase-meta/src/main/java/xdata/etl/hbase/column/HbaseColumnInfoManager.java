package xdata.etl.hbase.column;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseExclude;
import xdata.etl.hbase.annotatins.HbaseRow;
import xdata.etl.hbase.entity.HbaseEntity;

public class HbaseColumnInfoManager {
	private Map<Class<? extends HbaseEntity>, HbaseColumnInfoIndex> columnInfoIndexs;

	public HbaseColumnInfoManager() {
		columnInfoIndexs = new HashMap<Class<? extends HbaseEntity>, HbaseColumnInfoIndex>();
	}

	public HbaseColumnInfoIndex getColumnInfoIndex(
			Class<? extends HbaseEntity> clazz) {
		return columnInfoIndexs.get(clazz);
	}

	public void addField(Class<? extends HbaseEntity> clazz, Field field) {
		Class<?> embeddedClass = getEmbeddableClass(field);
		if (embeddedClass == null) {
			HbaseColumnInfo columnInfo = new HbaseColumnInfo(clazz);
			columnInfo.setColumnName(getColumnName(field));
			columnInfo.setField(field);
			addColumnInfo(columnInfo);
		} else {
			addEmbedded(clazz, field, embeddedClass);
		}
	}

	private void addEmbedded(Class<? extends HbaseEntity> clazz,
			Field embeddFiled, Class<?> embeddedClass) {
		List<Field> fields = getAllFields(embeddFiled.getType());
		for (Field field : fields) {
			if (isExcludeMod(field.getModifiers())) {
				continue;
			}
			if (isExcludeField(field)) {
				continue;
			}
			HbaseColumnInfo columnInfo = new HbaseColumnInfo(clazz);
			columnInfo.setColumnName(getColumnName(field));
			columnInfo.setEmbeddClass(embeddedClass);
			columnInfo.setEmbeddFiled(embeddFiled);
			columnInfo.setField(field);
			addColumnInfo(columnInfo);
		}
	}

	public void addColumnInfo(HbaseColumnInfo columnInfo) {
		Class<? extends HbaseEntity> clazz = columnInfo.getOwner();
		if (!columnInfoIndexs.containsKey(clazz)) {
			HbaseColumnInfoIndex index = new HbaseColumnInfoIndex(clazz);
			columnInfoIndexs.put(clazz, index);
		}
		HbaseColumnInfoIndex index = columnInfoIndexs.get(clazz);
		index.addColumnInfo(columnInfo);
	}

	public static String getColumnName(Field field) {
		HbaseColumn column = field.getAnnotation(HbaseColumn.class);
		if (column == null || column.name() == null || column.name().equals("")) {
			return field.getName();
		}
		return column.name();
	}

	public static Class<?> getEmbeddableClass(Field field) {
		HbaseEmbedded embedded = field.getAnnotation(HbaseEmbedded.class);
		if (embedded != null) {
			return field.getType();
		}
		return null;
	}

	public static boolean isExcludeField(Field field) {
		Annotation[] annotatins = field.getAnnotations();
		for (Annotation annotation : annotatins) {
			Class<?> annotationType = annotation.annotationType();
			if (annotationType.equals(HbaseRow.class)
					|| annotationType.equals(HbaseExclude.class)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isKafkaExcludeField(Field field) {
		return false;
	}

	public static boolean isExcludeMod(int modifier) {
		return Modifier.isStatic(modifier) || Modifier.isFinal(modifier)
				|| Modifier.isTransient(modifier);
	}

	public static List<Field> getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null && !superClass.equals(Object.class)) {
			fields.addAll(getAllFields(superClass));
		}
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		return fields;
	}

}
