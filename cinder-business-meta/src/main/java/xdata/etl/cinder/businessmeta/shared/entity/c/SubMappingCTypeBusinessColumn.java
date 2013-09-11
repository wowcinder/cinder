/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.c;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessColumnType;
import xdata.etl.cinder.businessmeta.shared.entity.SubMappingBusinessColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_column_c_sub_mapping")
public class SubMappingCTypeBusinessColumn extends CTypeBusinessColumn
		implements SubMappingBusinessColumn<CTypeBusinessToHbaseTableMapping> {
	private static final long serialVersionUID = -627511858919210694L;
	@ManyToOne
	@JoinColumn(name = "sub_mapping_id")
	private CTypeBusinessToHbaseTableMapping subMapping;

	public CTypeBusinessToHbaseTableMapping getSubMapping() {
		return subMapping;
	}

	public void setSubMapping(CTypeBusinessToHbaseTableMapping subMapping) {
		this.subMapping = subMapping;
	}

	@Override
	public BusinessColumnType getColumnType() {
		return BusinessColumnType.SUB_MAPPING;
	}
}
