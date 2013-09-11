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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "btype", length = 20)
public abstract class Business extends EntityHasTimeStampImpl {
	private static final long serialVersionUID = -5376450072498870497L;
	private Integer id;
	private String name;
	private BusinessType type;
	private List<BusinessVersion> versions;
	private String desc;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@NotNull
	@Column(nullable = false, unique = true, length = 40)
	@Length(min = 1, max = 40)
	public String getName() {
		return name;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "btype", insertable = false, updatable = false)
	public BusinessType getType() {
		return type;
	}

	@OneToMany(mappedBy = "business")
	public List<? extends BusinessVersion> getVersions() {
		return versions;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(BusinessType type) {
		this.type = type;
	}

	public void setVersions(List<BusinessVersion> versions) {
		this.versions = versions;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
