/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.c;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessCType;
import xdata.etl.cinder.businessmeta.shared.entity.BusinessVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@Entity
@DiscriminatorValue(BusinessType.Names.C_TYPE)
public class CTypeBusinessVersion extends BusinessVersion<BusinessCType> {
	private static final long serialVersionUID = -4030622326728311903L;

	@Override
	public BusinessCType createBusinessType() {
		return new BusinessCType();
	}

	@Override
	public CTypeBusiness getBusiness() {
		return (CTypeBusiness) super.getBusiness();
	}

}
