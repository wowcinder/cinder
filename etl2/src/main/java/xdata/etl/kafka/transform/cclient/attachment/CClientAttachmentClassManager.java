package xdata.etl.kafka.transform.cclient.attachment;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import xdata.etl.hbase.entity.HbaseAttachment;
import xdata.etl.hbase.entity.HbaseEntity;

public class CClientAttachmentClassManager {

	private Map<Class<? extends HbaseEntity>, Class<? extends HbaseAttachment>> mainPartClassToAttachmentClassMap;
	private Map<Class<? extends HbaseAttachment>, Class<? extends HbaseEntity>> attachmentClassToMainPartClassMap;
	private Map<Class<? extends HbaseEntity>, Field> mainPartClassToFiled;

	public CClientAttachmentClassManager() {
		mainPartClassToAttachmentClassMap = new HashMap<Class<? extends HbaseEntity>, Class<? extends HbaseAttachment>>();
		attachmentClassToMainPartClassMap = new HashMap<Class<? extends HbaseAttachment>, Class<? extends HbaseEntity>>();
		mainPartClassToFiled = new HashMap<Class<? extends HbaseEntity>, Field>();
	}

	public Class<? extends HbaseEntity> getMainPartClass(
			Class<? extends HbaseAttachment> attachmentClass) {
		return attachmentClassToMainPartClassMap.get(attachmentClass);
	}

	public Class<? extends HbaseAttachment> getAttachmentClass(
			Class<? extends HbaseEntity> mainPartClass) {
		return mainPartClassToAttachmentClassMap.get(mainPartClass);
	}

	public void add(Class<? extends HbaseEntity> mainPartClass, Field field) {
		Class<? extends HbaseAttachment> attachmentClass = getAttachmentClass(field);
		if (attachmentClass != null) {
			add(mainPartClass, attachmentClass, field);
		}
	}

	private void add(Class<? extends HbaseEntity> mainPartClass,
			Class<? extends HbaseAttachment> attachmentClass, Field field) {
		mainPartClassToAttachmentClassMap.put(mainPartClass, attachmentClass);
		attachmentClassToMainPartClassMap.put(attachmentClass, mainPartClass);
		mainPartClassToFiled.put(mainPartClass, field);
	}

	@SuppressWarnings({ "unchecked" })
	private Class<? extends HbaseAttachment> getAttachmentClass(Field field) {
		ParameterizedType type = (ParameterizedType) field.getGenericType();
		Class<?> clazz = (Class<?>) type.getActualTypeArguments()[0];
		if (HbaseEntity.class.isAssignableFrom(clazz)) {
			return (Class<? extends HbaseAttachment>) clazz;
		}
		return null;
	}

	public Map<Class<? extends HbaseEntity>, Field> getMainPartClassToFiled() {
		return mainPartClassToFiled;
	}

}
