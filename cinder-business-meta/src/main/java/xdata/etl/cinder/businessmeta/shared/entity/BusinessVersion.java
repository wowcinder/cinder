/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_version")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "btype", length = 20)
public abstract class BusinessVersion extends EntityHasTimeStampImpl {
	private static final long serialVersionUID = -4849509631471684174L;

	private Integer id;
	private String version;
	private BusinessToHbaseTableMapping mapping;
	protected Business business;
	private BusinessType type;
	private String desc;

	public BusinessVersion() {
		version = "0.0";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Column(length = 40, nullable = true)
	@Length(min = 1, max = 40)
	public String getVersion() {
		return version;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "mapping_id")
	public BusinessToHbaseTableMapping getMapping() {
		return mapping;
	}

	@ManyToOne
	@JoinColumn(name = "business_id")
	public Business getBusiness() {
		return business;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "btype", insertable = false, updatable = false)
	public BusinessType getType() {
		return type;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setMapping(BusinessToHbaseTableMapping mapping) {
		this.mapping = mapping;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public void setType(BusinessType type) {
		this.type = type;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
