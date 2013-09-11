/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.c;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.entity.BusinessVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue(BusinessType.Names.C_TYPE)
public class CTypeBusinessVersion extends BusinessVersion {
	private static final long serialVersionUID = -8334018254059047467L;

	public CTypeBusiness getBusiness() {
		return (CTypeBusiness) business;
	}

}
