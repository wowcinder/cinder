/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessTypeEnum;
import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@MappedSuperclass
public abstract class BusinessSuperclass<T extends BusinessType> extends
		EntityHasTimeStampImpl {
	private static final long serialVersionUID = 5876578611327859795L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Transient
	protected T businessType;

	public Integer getId() {
		return id;
	}

	public BusinessTypeEnum getBtype() {
		return getBusinessType().getType();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public T getBusinessType() {
		if (businessType == null) {
			businessType = createBusinessType();
		}
		return businessType;
	}

	public void setBusinessType(T businessType) {
		this.businessType = businessType;
	}

	public abstract T createBusinessType();

}
