package xdata.etl.kafka.transform.cclient.column;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xdata.etl.hbase.entity.HbaseEntity;

public class CClientColumnInfoManager {
	private Map<Class<? extends HbaseEntity>, List<CClientColumnInfo>> entityClassToColumnInfos;

	public CClientColumnInfoManager() {
		entityClassToColumnInfos = new HashMap<Class<? extends HbaseEntity>, List<CClientColumnInfo>>();
	}

	public void add(Class<? extends HbaseEntity> clazz, Field field) {
		if (field.getName() == "mainPartRow") {
			return;
		}
		if (!entityClassToColumnInfos.containsKey(clazz)) {
			entityClassToColumnInfos.put(clazz,
					new ArrayList<CClientColumnInfo>());
		}
		entityClassToColumnInfos.get(clazz).add(new CClientColumnInfo(field));
	}

	public List<CClientColumnInfo> getColumnInfos(
			Class<? extends HbaseEntity> clazz) {
		return entityClassToColumnInfos.get(clazz);
	}

	public Map<Class<? extends HbaseEntity>, List<CClientColumnInfo>> getEntityClassToColumnInfos() {
		return entityClassToColumnInfos;
	}

}
