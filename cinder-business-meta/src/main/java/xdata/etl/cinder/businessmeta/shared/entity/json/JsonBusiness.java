/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.json;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessJsonType;
import xdata.etl.cinder.businessmeta.shared.entity.Business;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue(BusinessType.Names.JSON_TYPE)
public class JsonBusiness extends Business<BusinessJsonType> {
	private static final long serialVersionUID = 2418545369320700277L;

	public JsonBusiness() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JsonBusinessVersion> getVersions() {
		return (List<JsonBusinessVersion>) super.getVersions();
	}

	@Override
	public BusinessJsonType createBusinessType() {
		return new BusinessJsonType();
	}

}
