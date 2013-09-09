/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.hbasemeta.shared.entity.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;

/**
 * @author XuehuiHe
 * @date 2013年8月14日
 */
@Entity
@Table(name = "hbase_table")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class HbaseTable extends EntityHasTimeStampImpl implements Serializable {
	private static final long serialVersionUID = -5625914468739750008L;

	private Integer id;
	private String name;
	private String shortname;
	private String desc;
	private List<HbaseTableVersion> versions;

	public HbaseTable() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Column(length = 60, unique = true)
	@NotNull
	@Length(min = 1, max = 60)
	public String getName() {
		return name;
	}

	@Column(length = 60)
	@NotNull
	@Length(min = 1, max = 60)
	public String getShortname() {
		return shortname;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	@OneToMany(mappedBy = "table", cascade = CascadeType.REMOVE)
	public List<HbaseTableVersion> getVersions() {
		return versions;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setVersions(List<HbaseTableVersion> versions) {
		this.versions = versions;
	}

}
