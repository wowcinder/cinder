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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessTypeEnum;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_2_hbase_table_mapping")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "btype", length = 20)
public abstract class BusinessToHbaseTableMapping<T extends BusinessType>
		extends BusinessSuperclass<T> {
	private static final long serialVersionUID = 6554099088661273243L;
	@ManyToOne
	@JoinColumn(name = "hbase_table_version_id")
	private HbaseTableVersion hbaseTableVersion;
	@OneToMany(mappedBy = "mapping")
	protected List<BusinessColumn<T>> columns;
	@Enumerated(EnumType.STRING)
	@Column(name = "btype", insertable = false, updatable = false)
	private BusinessTypeEnum btype;
	@Column(name = "description", columnDefinition = "text")
	private String desc;

	public BusinessToHbaseTableMapping() {
	}

	public HbaseTableVersion getHbaseTableVersion() {
		return hbaseTableVersion;
	}

	public List<? extends BusinessColumn<T>> getColumns() {
		return columns;
	}

	public BusinessTypeEnum getBtype() {
		if (btype == null) {
			return super.getBtype();
		}
		return btype;
	}

	public String getDesc() {
		return desc;
	}

	public void setHbaseTableVersion(HbaseTableVersion hbaseTableVersion) {
		this.hbaseTableVersion = hbaseTableVersion;
	}

	public void setColumns(List<BusinessColumn<T>> columns) {
		this.columns = columns;
	}

	public void setBtype(BusinessTypeEnum btype) {
		this.btype = btype;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
