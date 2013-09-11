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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.entity.ref.BusinessColumnRef;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_column")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BusinessColumn<T extends BusinessType> extends
		BusinessSuperclass<T> {
	private static final long serialVersionUID = 1930104423164074284L;
	@ManyToOne
	@JoinColumn(name = "mapping_id")
	private BusinessToHbaseTableMapping<T> mapping;
	@OneToOne
	@JoinColumn(name = "ref_id", nullable = false)
	private BusinessColumnRef columnRef;
	@Column(name = "description", columnDefinition = "text")
	private String desc;

	public BusinessColumn() {
	}

	public BusinessToHbaseTableMapping<T> getMapping() {
		return mapping;
	}

	public String getDesc() {
		return desc;
	}

	public void setMapping(BusinessToHbaseTableMapping<T> mapping) {
		this.mapping = mapping;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BusinessColumnRef getColumnRef() {
		return columnRef;
	}

	public void setColumnRef(BusinessColumnRef columnRef) {
		this.columnRef = columnRef;
	}

}
