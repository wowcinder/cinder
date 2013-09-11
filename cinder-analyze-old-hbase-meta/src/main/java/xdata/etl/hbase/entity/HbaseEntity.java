package xdata.etl.hbase.entity;

import java.util.Date;

import xdata.etl.hbase.annotatins.HbaseExclude;
import xdata.etl.hbase.annotatins.HbaseRow;
import xdata.etl.hbase.annotatins.HbaseTable;

/**
 * Hbase entity 基础类
 * 
 * @author XuehuiHe
 * 
 */
public class HbaseEntity {
	@HbaseRow
	private String row;
	private Date stamp;

	@HbaseExclude
	private String raw;

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public Date getStamp() {
		return stamp;
	}

	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getTableName() {
		HbaseTable table = this.getClass().getAnnotation(HbaseTable.class);
		if (table == null) {
			return this.getClass().getSimpleName();
		}
		return table.name();
	}

}
