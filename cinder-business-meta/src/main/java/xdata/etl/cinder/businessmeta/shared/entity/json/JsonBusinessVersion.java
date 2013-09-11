/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.json;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.entity.BusinessVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue(BusinessType.Names.JSON_TYPE)
public class JsonBusinessVersion extends BusinessVersion {
	private static final long serialVersionUID = 7191018783459845393L;

	public JsonBusiness getBusiness() {
		return (JsonBusiness) business;
	}
}
