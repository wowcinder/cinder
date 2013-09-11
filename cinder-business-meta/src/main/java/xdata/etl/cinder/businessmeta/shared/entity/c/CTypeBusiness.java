/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.c;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessCType;
import xdata.etl.cinder.businessmeta.shared.entity.Business;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue(BusinessType.Names.C_TYPE)
public class CTypeBusiness extends Business<BusinessCType> {
	private static final long serialVersionUID = 6485751341866927907L;

	public CTypeBusiness() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CTypeBusinessVersion> getVersions() {
		return (List<CTypeBusinessVersion>) super.getVersions();
	}

	@Override
	public BusinessCType createBusinessType() {
		return new BusinessCType();
	}

}
