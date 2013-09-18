package xdata.etl.kafka.transform.cclient;

import java.lang.reflect.Field;
import java.util.List;

import xdata.etl.hbase.column.HbaseColumnInfoManager;
import xdata.etl.hbase.entity.HbaseAttachment;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.exception.TerminalFieldValueParseException;
import xdata.etl.kafka.transform.KafkaTransformer;
import xdata.etl.kafka.transform.cclient.attachment.CClientAttachmentClassManager;
import xdata.etl.kafka.transform.cclient.attachment.CClientAttachmentRepeatPositionManager;
import xdata.etl.kafka.transform.cclient.column.CClientColumnInfoManager;
import xdata.etl.kafka.transform.cclient.data.AbstractCClientDataSwapper;
import xdata.etl.kafka.transform.handler.KafkaTransformHandler;
import xdata.etl.kafka.transform.result.DefaultTransformResult;
import xdata.etl.kafka.transform.result.KafkaTransformResult;
import xdata.etl.util.ClassScaner;
import xdata.etl.util.ClassScaner.HbaseEntityClassFilter;

public abstract class AbstractCClientTransformer implements KafkaTransformer {

	private CClientColumnInfoManager columnInfoManager;
	private CClientAttachmentClassManager cClientAttachmentClassManager;

	public AbstractCClientTransformer(ClassScaner entityClassScaner) {
		columnInfoManager = new CClientColumnInfoManager();
		cClientAttachmentClassManager = new CClientAttachmentClassManager();
		HbaseEntityClassFilter filter = new HbaseEntityClassFilter();

		for (Class<? extends HbaseEntity> clazz : filter
				.filte(entityClassScaner)) {
			analyze(clazz);
		}
	}

	public KafkaTransformResult transform(Class<? extends HbaseEntity> clazz,
			String raw, KafkaTransformHandler... handlers)
			throws KafkaTransformException, TerminalFieldValueParseException {
		AbstractCClientDataSwapper dataSwapper = generateDataSwapper(clazz, raw);
		HbaseEntity entity = dataSwapper.build();
		entity.setRaw(raw);

		KafkaTransformResult result = new DefaultTransformResult();
		result.add(entity);
		for (KafkaTransformHandler handler : handlers) {
			handler.handler(result);
		}
		return result;
	}

	protected AbstractCClientDataSwapper generateDataSwapper(
			Class<? extends HbaseEntity> clazz, String raw) {
		AbstractCClientDataSwapper dataSwapper = newDataSwapper(raw);
		// repeatIndex
		dataSwapper.setRepeatStartIndex(CClientAttachmentRepeatPositionManager
				.getRepeatIndex(clazz));
		// main
		dataSwapper.setMainPartColumnInfos(getColumnInfoManager()
				.getColumnInfos(clazz));
		dataSwapper.setMainPartClass(clazz);
		Class<? extends HbaseAttachment> attachmentClass = getCClientAttachmentClassManager()
				.getAttachmentClass(clazz);

		// attachment attachmentField
		if (attachmentClass != null) {
			dataSwapper.setAttachmentClass(attachmentClass);
			dataSwapper.setAttachmentColumnInfos(getColumnInfoManager()
					.getColumnInfos(attachmentClass));
			dataSwapper.setAttachmentField(getCClientAttachmentClassManager()
					.getMainPartClassToFiled().get(clazz));
		}

		return dataSwapper;
	}

	abstract protected AbstractCClientDataSwapper newDataSwapper(String raw);

	private void analyze(Class<? extends HbaseEntity> clazz) {
		List<Field> fields = HbaseColumnInfoManager.getAllFields(clazz);
		for (Field field : fields) {
			if (HbaseColumnInfoManager.isExcludeMod(field.getModifiers())) {
				continue;
			}
			if (HbaseColumnInfoManager.isKafkaExcludeField(field)) {
				continue;
			}
			if (HbaseAttachmentList.class.isAssignableFrom(field.getType())) {
				cClientAttachmentClassManager.add(clazz, field);
			} else if (HbaseEntity.class.isAssignableFrom(field.getType())) {

			} else {
				columnInfoManager.add(clazz, field);
			}
		}
	}

	public CClientColumnInfoManager getColumnInfoManager() {
		return columnInfoManager;
	}

	public CClientAttachmentClassManager getCClientAttachmentClassManager() {
		return cClientAttachmentClassManager;
	}

}
