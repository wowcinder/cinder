/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import xdata.etl.cinder.businessmeta.shared.BusinessColumnType.BusinessColumnTypeEnum;
import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@Entity
@Table(name = "business_column_ref")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BusinessColumnRef extends EntityHasTimeStampImpl {

	private static final long serialVersionUID = 1465433232866015341L;
	private Integer id;
	private BusinessColumnTypeEnum columnType;

	public BusinessColumnRef() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ctype", nullable = false, updatable = false)
	public BusinessColumnTypeEnum getColumnType() {
		return columnType;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setColumnType(BusinessColumnTypeEnum columnType) {
		this.columnType = columnType;
	}

}
