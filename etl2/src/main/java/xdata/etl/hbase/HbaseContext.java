package xdata.etl.hbase;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.springframework.core.io.Resource;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.attachment.HbaseAttachmentManager;
import xdata.etl.hbase.column.HbaseColumnInfoManager;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.hbase.exception.RowGenerateException;
import xdata.etl.hbase.lazy.IHbaseLazySaveContainerFactory;
import xdata.etl.hbase.row.HbaseRowGeneratorManager;
import xdata.etl.hbase.tablename.HbaseTableNameManager;
import xdata.etl.hbase.transform.HbaseTransformer;
import xdata.etl.hbase.transform.HbaseTransformer.HbaseTransformResult;
import xdata.etl.hbase.transform.HbaseTransformer.HbaseTransformResultCollection;
import xdata.etl.util.ClassScaner;
import xdata.etl.util.ClassScaner.HbaseEntityClassFilter;

/**
 * Hbase上下文类
 * 
 * 注：1.该上下文目前支持oneToMany 2.rowID固定为row字段
 * 
 * @author XuehuiHe
 * 
 */

public class HbaseContext {
	private Configuration cfg;

	private HbaseColumnInfoManager columnInfoManager;
	private HbaseTableNameManager tableNameManager;
	private HbaseAttachmentManager attachmentManager;
	private HbaseRowGeneratorManager rowGeneratorManager;
	private HbaseTransformer transformer;
	private IHbaseLazySaveContainerFactory lazySaveContainerFactory;

	private HbaseContext() {
		columnInfoManager = new HbaseColumnInfoManager();
		tableNameManager = new HbaseTableNameManager();
		attachmentManager = new HbaseAttachmentManager();
		rowGeneratorManager = new HbaseRowGeneratorManager();
		transformer = new HbaseTransformer(this);
		cfg = HBaseConfiguration.create();
	}

	public void save(HbaseEntity t) throws IOException, RowGenerateException {
		HbaseTransformResult result = transformer.transform(t);
		HbaseTransformResultCollection c = new HbaseTransformResultCollection();
		c.merge(result);
		save(c);
	}

	public void save(List<HbaseEntity> list) throws IOException,
			RowGenerateException {
		HbaseTransformResultCollection c = new HbaseTransformResultCollection();
		for (HbaseEntity hbaseEntity : list) {
			HbaseTransformResult result = transformer.transform(hbaseEntity);
			c.merge(result);
		}
		save(c);
	}

	public void save(HbaseTransformResultCollection c) throws IOException {
		Map<String, List<Put>> map = c.getPutsMap();
		Set<String> tableNames = map.keySet();
		for (String tableName : tableNames) {
			save(tableName, map.get(tableName));
		}
	}

	public void save(String tableName, List<Put> list) throws IOException {
		HTable htable = new HTable(getCfg(), tableName);
		htable.put(list);
		htable.flushCommits();
		htable.close();
	}

	public void setHbaseConfigPath(Resource hbaseConfigPath) throws IOException {
		if (hbaseConfigPath.exists()) {
			cfg.addResource(hbaseConfigPath.getURL());
		}
	}

	private void analyzeHbaseClass(Class<? extends HbaseEntity> clazz) {
		HbaseTable table = clazz.getAnnotation(HbaseTable.class);
		if (table == null) {
			return;
		}
		tableNameManager.analyzeHbaseTable(clazz);
		rowGeneratorManager.analyzeHbaseTable(clazz);
		List<Field> fields = HbaseColumnInfoManager.getAllFields(clazz);
		for (Field field : fields) {
			if (HbaseColumnInfoManager.isExcludeMod(field.getModifiers())) {
				continue;
			}
			if (HbaseColumnInfoManager.isExcludeField(field)) {
				continue;
			}
			if (HbaseEntity.class.isAssignableFrom(field.getType())) {

			} else if (Collection.class.isAssignableFrom(field.getType())) {
				attachmentManager.add(clazz, field);
			} else {// 基本属性
				columnInfoManager.addField(clazz, field);
			}
		}
	}

	public Configuration getCfg() {
		return cfg;
	}

	public HbaseColumnInfoManager getColumnInfoManager() {
		return columnInfoManager;
	}

	public HbaseTableNameManager getTableNameManager() {
		return tableNameManager;
	}

	public HbaseAttachmentManager getAttachmentManager() {
		return attachmentManager;
	}

	public String getRow(HbaseEntity t) throws RowGenerateException {
		String row = rowGeneratorManager.getRow(t);
		if (row == null) {
			throw new RowGenerateException(t);
		}
		return row;
	}

	public HbaseTransformer getTransformer() {
		return transformer;
	}

	public void setTransformer(HbaseTransformer transformer) {
		this.transformer = transformer;
	}

	public IHbaseLazySaveContainerFactory getLazySaveContainerFactory() {
		return lazySaveContainerFactory;
	}

	public void setLazySaveContainerFactory(
			IHbaseLazySaveContainerFactory lazySaveContainerFactory) {
		lazySaveContainerFactory.setHbaseCxt(this);
		this.lazySaveContainerFactory = lazySaveContainerFactory;
	}

	public void setEntityClassScaner(final ClassScaner entityClassScaner) {
		HbaseEntityClassFilter filter = new HbaseEntityClassFilter();
		List<Class<? extends HbaseEntity>> list = filter
				.filte(entityClassScaner);
		for (Class<? extends HbaseEntity> clazz : list) {
			analyzeHbaseClass(clazz);
		}
	}
}
