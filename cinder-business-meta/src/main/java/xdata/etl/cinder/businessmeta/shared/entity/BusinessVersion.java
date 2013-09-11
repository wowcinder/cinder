/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessTypeEnum;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_version")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "btype", length = 20)
public abstract class BusinessVersion<T extends BusinessType> extends
		BusinessSuperclass<T> {
	private static final long serialVersionUID = -4849509631471684174L;
	@Column(length = 40, nullable = false)
	@Length(min = 1, max = 40)
	private String version;
	@ManyToOne(optional = false)
	@JoinColumn(name = "mapping_id")
	private BusinessToHbaseTableMapping<T> mapping;
	@ManyToOne
	@JoinColumn(name = "business_id")
	protected Business<T> business;
	@Enumerated(EnumType.STRING)
	@Column(name = "btype", insertable = false, updatable = false)
	private BusinessTypeEnum btype;
	@Column(name = "description", columnDefinition = "text")
	private String desc;

	public String getVersion() {
		return version;
	}

	public BusinessToHbaseTableMapping<T> getMapping() {
		return mapping;
	}

	public Business<T> getBusiness() {
		return business;
	}

	public BusinessTypeEnum getBtype() {
		if (this.btype == null) {
			return super.getBtype();
		}
		return btype;
	}

	public String getDesc() {
		return desc;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setMapping(BusinessToHbaseTableMapping<T> mapping) {
		this.mapping = mapping;
	}

	public void setBusiness(Business<T> business) {
		this.business = business;
	}

	public void setBtype(BusinessTypeEnum btype) {
		this.btype = btype;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
