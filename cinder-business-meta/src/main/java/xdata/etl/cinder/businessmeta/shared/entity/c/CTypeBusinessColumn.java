/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.c;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.entity.BusinessColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_column_c")
public abstract class CTypeBusinessColumn extends BusinessColumn {
	private static final long serialVersionUID = -8540826006059877751L;
	@Column(nullable = false)
	private int pos;

	public CTypeBusinessColumn() {
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	@Override
	public BusinessType getBusinessType() {
		return BusinessType.C_TYPE;
	}

	public CTypeBusinessColumn getSelf() {
		return this;
	}
}
