/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.json;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessJsonType;
import xdata.etl.cinder.businessmeta.shared.entity.BusinessToHbaseTableMapping;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue(BusinessType.Names.JSON_TYPE)
public class JsonBusinessToHbaseTableMapping extends
		BusinessToHbaseTableMapping<BusinessJsonType> {
	private static final long serialVersionUID = 6878342659450824345L;

	@Override
	public BusinessJsonType createBusinessType() {
		return new BusinessJsonType();
	}
}
