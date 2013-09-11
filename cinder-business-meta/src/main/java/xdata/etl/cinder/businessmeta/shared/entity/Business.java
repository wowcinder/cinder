/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessTypeEnum;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "btype", length = 20)
public abstract class Business<T extends BusinessType> extends
		BusinessSuperclass<T> {
	private static final long serialVersionUID = -5376450072498870497L;
	@NotNull
	@Column(nullable = false, unique = true, length = 40)
	@Length(min = 1, max = 40)
	private String name;
	@Column(name = "description", columnDefinition = "text")
	private String desc;
	@OneToMany(mappedBy = "business", targetEntity = BusinessVersion.class)
	private List<BusinessVersion<T>> versions;
	@Enumerated(EnumType.STRING)
	@Column(name = "btype", insertable = false, updatable = false)
	private BusinessTypeEnum btype;

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public List<? extends BusinessVersion<T>> getVersions() {
		return versions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setVersions(List<BusinessVersion<T>> versions) {
		this.versions = versions;
	}

	public BusinessTypeEnum getBtype() {
		if (this.btype == null) {
			return super.getBtype();
		}
		return btype;
	}

	public void setBtype(BusinessTypeEnum btype) {
		this.btype = btype;
	}

}
