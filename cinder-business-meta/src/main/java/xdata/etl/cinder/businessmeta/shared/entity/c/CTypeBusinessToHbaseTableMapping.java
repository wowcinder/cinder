/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.c;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessCType;
import xdata.etl.cinder.businessmeta.shared.entity.BusinessToHbaseTableMapping;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue(BusinessType.Names.C_TYPE)
public class CTypeBusinessToHbaseTableMapping extends
		BusinessToHbaseTableMapping<BusinessCType> {

	private static final long serialVersionUID = 3254709662221405450L;

	@Override
	public BusinessCType createBusinessType() {
		return new BusinessCType();
	}

}
