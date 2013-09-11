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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_2_hbase_table_mapping")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "btype", length = 20)
public abstract class BusinessToHbaseTableMapping extends
		EntityHasTimeStampImpl {
	private static final long serialVersionUID = 6554099088661273243L;

	private Integer id;
	private HbaseTableVersion hbaseTableVersion;
	protected List<BusinessColumn> columns;
	private BusinessType type;
	private String desc;

	public BusinessToHbaseTableMapping() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "hbase_table_version_id")
	public HbaseTableVersion getHbaseTableVersion() {
		return hbaseTableVersion;
	}

	@OneToMany(mappedBy = "mapping")
	public List<? extends BusinessColumn> getColumns() {
		return columns;
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

	public void setHbaseTableVersion(HbaseTableVersion hbaseTableVersion) {
		this.hbaseTableVersion = hbaseTableVersion;
	}

	public void setColumns(List<BusinessColumn> columns) {
		this.columns = columns;
	}

	public void setType(BusinessType type) {
		this.type = type;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
