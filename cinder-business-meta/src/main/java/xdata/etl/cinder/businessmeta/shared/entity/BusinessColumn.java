/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessColumnType;
import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_column")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BusinessColumn extends EntityHasTimeStampImpl {
	private static final long serialVersionUID = 1930104423164074284L;

	@ManyToOne
	@JoinColumn(name = "mapping_id")
	private BusinessToHbaseTableMapping mapping;

	@Column(name = "description", columnDefinition = "text")
	private String desc;

	public BusinessColumn() {
	}

	public BusinessToHbaseTableMapping getMapping() {
		return mapping;
	}

	public void setMapping(BusinessToHbaseTableMapping mapping) {
		this.mapping = mapping;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public abstract BusinessColumnType getColumnType();

	public abstract BusinessType getBusinessType();

}
