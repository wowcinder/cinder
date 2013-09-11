/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.ref;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xdata.etl.cinder.businessmeta.shared.BusinessColumnType.BusinessColumnTypeEnum;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@Entity
@Table(name = "business_column_simple_ref")
public class SimpleBusinessColumnRef extends BusinessColumnRef {
	private static final long serialVersionUID = 2945258659344053349L;
	private HbaseTableColumn hbaseTableColumn;

	public SimpleBusinessColumnRef() {
		setColumnType(BusinessColumnTypeEnum.SIMPLE);
	}

	@ManyToOne
	@JoinColumn(name = "hbase_table_column_id")
	public HbaseTableColumn getHbaseTableColumn() {
		return hbaseTableColumn;
	}

	public void setHbaseTableColumn(HbaseTableColumn hbaseTableColumn) {
		this.hbaseTableColumn = hbaseTableColumn;

	}
}
