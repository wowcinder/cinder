package xdata.etl.hbase.tablename;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

public class HbaseTableNameManager {
	private Map<String, Class<? extends HbaseEntity>> tableNameToEntityClass = new HashMap<String, Class<? extends HbaseEntity>>();
	private Map<Class<? extends HbaseEntity>, String> entityClassToTableName = new HashMap<Class<? extends HbaseEntity>, String>();

	public void addTable(String tablename, Class<? extends HbaseEntity> clazz) {
		tableNameToEntityClass.put(tablename, clazz);
		entityClassToTableName.put(clazz, tablename);
	}

	public void analyzeHbaseTable(Class<? extends HbaseEntity> clazz) {
		HbaseTable table = clazz.getAnnotation(HbaseTable.class);
		String name = null;
		if (table != null) {
			name = table.name();
		} else {
			name = clazz.getSimpleName();
		}
		addTable(name, clazz);
	}

	public String getTableName(Class<? extends HbaseEntity> clazz) {
		return entityClassToTableName.get(clazz);
	}

	public List<String> getTableNames() {
		List<String> list = new ArrayList<String>();
		list.addAll(tableNameToEntityClass.keySet());
		return list;
	}

	public Class<? extends HbaseEntity> getTableClass(String tablename) {
		return tableNameToEntityClass.get(tablename);
	}
}
