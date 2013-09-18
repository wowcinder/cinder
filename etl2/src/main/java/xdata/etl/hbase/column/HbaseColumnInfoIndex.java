package xdata.etl.hbase.column;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xdata.etl.hbase.entity.HbaseEntity;

public class HbaseColumnInfoIndex {
	private Class<? extends HbaseEntity> owner;
	private Map<Field, HbaseColumnInfo> fieldToInfo;
	private Map<String, HbaseColumnInfo> columnNameToInfo;
	private List<HbaseColumnInfo> columnInfos;

	public HbaseColumnInfoIndex(Class<? extends HbaseEntity> clazz) {
		fieldToInfo = new HashMap<Field, HbaseColumnInfo>();
		columnNameToInfo = new HashMap<String, HbaseColumnInfo>();
		columnInfos = new ArrayList<HbaseColumnInfo>();
		this.owner = clazz;
	}

	public HbaseColumnInfo getColumnInfo(Field field) {
		return getFieldToInfo().get(field);
	}

	public HbaseColumnInfo getColumnInfo(String columnName) {
		return getColumnNameToInfo().get(columnName);
	}

	public void addColumnInfo(HbaseColumnInfo hbaseColumnInfo) {
		this.columnInfos.add(hbaseColumnInfo);
		fieldToInfo.put(hbaseColumnInfo.getField(), hbaseColumnInfo);
		columnNameToInfo.put(hbaseColumnInfo.getColumnName(), hbaseColumnInfo);
	}

	public void setColumnInfos(List<HbaseColumnInfo> columnInfos) {
		this.columnInfos = columnInfos;
		for (HbaseColumnInfo hbaseColumnInfo : columnInfos) {
			fieldToInfo
					.put(hbaseColumnInfo.getField(), hbaseColumnInfo);
			columnNameToInfo.put(hbaseColumnInfo.getColumnName(),
					hbaseColumnInfo);
		}
	}

	public Class<? extends HbaseEntity> getOwner() {
		return owner;
	}

	public Map<Field, HbaseColumnInfo> getFieldToInfo() {
		return fieldToInfo;
	}

	public Map<String, HbaseColumnInfo> getColumnNameToInfo() {
		return columnNameToInfo;
	}

	public List<HbaseColumnInfo> getColumnInfos() {
		return columnInfos;
	}
}
