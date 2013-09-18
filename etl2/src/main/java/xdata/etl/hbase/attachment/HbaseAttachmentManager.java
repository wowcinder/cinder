package xdata.etl.hbase.attachment;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xdata.etl.hbase.entity.HbaseAttachment;
import xdata.etl.hbase.entity.HbaseEntity;

public class HbaseAttachmentManager {
	private Map<Class<? extends HbaseEntity>, List<Field>> mainPartClassToAttachmentFields;
	private Map<Field, Class<? extends HbaseEntity>> attachmentFieldToMainPartClass;
	private Map<Class<? extends HbaseAttachment>, Field> attachmentClassToField;
	private Map<Field, Class<? extends HbaseAttachment>> fieldToAttachmentClass;

	public HbaseAttachmentManager() {
		mainPartClassToAttachmentFields = new HashMap<Class<? extends HbaseEntity>, List<Field>>();
		attachmentFieldToMainPartClass = new HashMap<Field, Class<? extends HbaseEntity>>();
		attachmentClassToField = new HashMap<Class<? extends HbaseAttachment>, Field>();
		fieldToAttachmentClass = new HashMap<Field, Class<? extends HbaseAttachment>>();
	}

	public Map<Class<? extends HbaseEntity>, List<Field>> getMainPartClassToAttachmentFields() {
		return mainPartClassToAttachmentFields;
	}

	public Map<Field, Class<? extends HbaseEntity>> getAttachmentFieldToMainPartClass() {
		return attachmentFieldToMainPartClass;
	}

	public Map<Class<? extends HbaseAttachment>, Field> getAttachmentClassToField() {
		return attachmentClassToField;
	}

	public void add(Class<? extends HbaseEntity> clazz, Field field) {
		ParameterizedType type = (ParameterizedType) field.getGenericType();
		Type[] types = type.getActualTypeArguments();
		if (types.length > 0) {
			Class<?> attachmentClass = (Class<?>) types[0];
			if (HbaseAttachment.class.isAssignableFrom(attachmentClass)) {
				@SuppressWarnings("unchecked")
				Class<? extends HbaseAttachment> attachmentClass2 = (Class<? extends HbaseAttachment>) attachmentClass;
				if (!mainPartClassToAttachmentFields.containsKey(clazz)) {
					mainPartClassToAttachmentFields.put(clazz,
							new ArrayList<Field>());
				}
				mainPartClassToAttachmentFields.get(clazz).add(field);
				attachmentFieldToMainPartClass.put(field, clazz);
				attachmentClassToField.put(attachmentClass2, field);
				fieldToAttachmentClass.put(field, attachmentClass2);
			}
		}
	}

	public Map<Field, Class<? extends HbaseAttachment>> getFieldToAttachmentClass() {
		return fieldToAttachmentClass;
	}

}
