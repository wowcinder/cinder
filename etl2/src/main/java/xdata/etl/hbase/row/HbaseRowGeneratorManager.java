package xdata.etl.hbase.row;

import java.util.HashMap;
import java.util.Map;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

public class HbaseRowGeneratorManager {
	private Map<Class<? extends HbaseEntity>, HbaseRowGenerator> map;
	private Map<Class<? extends HbaseRowGenerator>, HbaseRowGenerator> rowGeneratorsMap;

	public HbaseRowGeneratorManager() {
		map = new HashMap<Class<? extends HbaseEntity>, HbaseRowGenerator>();
		rowGeneratorsMap = new HashMap<Class<? extends HbaseRowGenerator>, HbaseRowGenerator>();
	}

	public void analyzeHbaseTable(Class<? extends HbaseEntity> clazz) {
		HbaseTable table = clazz.getAnnotation(HbaseTable.class);
		Class<? extends HbaseRowGenerator> rowGeneratorClass = null;
		if (table != null) {
			rowGeneratorClass = table.rowGenerator();
		} else {
			rowGeneratorClass = HbaseDefaultRowGenerator.class;
		}
		add(clazz, rowGeneratorClass);
	}

	public String getRow(HbaseEntity t) {
		HbaseRowGenerator rowGenerator = map.get(t.getClass());
		return rowGenerator.generate(t);
	}

	public void add(Class<? extends HbaseEntity> clazz1,
			Class<? extends HbaseRowGenerator> clazz2) {
		add(clazz1, createRowGenerator(clazz2));
	}

	private HbaseRowGenerator createRowGenerator(
			Class<? extends HbaseRowGenerator> clazz) {
		if (rowGeneratorsMap.containsKey(clazz)) {
			return rowGeneratorsMap.get(clazz);
		}
		HbaseRowGenerator rowGenerator = null;
		try {
			rowGenerator = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		rowGeneratorsMap.put(clazz, rowGenerator);
		return rowGenerator;

	}

	public void add(Class<? extends HbaseEntity> clazz1,
			HbaseRowGenerator rowGenerator) {
		if (rowGenerator == null) {
			return;
		}
		map.put(clazz1, rowGenerator);

	}
}
