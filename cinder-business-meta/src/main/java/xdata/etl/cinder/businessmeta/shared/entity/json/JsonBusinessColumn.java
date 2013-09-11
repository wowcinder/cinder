/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.json;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.businessmeta.shared.BusinessType;
import xdata.etl.cinder.businessmeta.shared.entity.BusinessColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_column_json")
public abstract class JsonBusinessColumn extends BusinessColumn {
	private static final long serialVersionUID = -3446438138303892156L;
	@Column(length = 200, nullable = false)
	@NotNull
	@Length(min = 1, max = 200)
	private String path;

	public JsonBusinessColumn() {
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public BusinessType getBusinessType() {
		return BusinessType.JSON_TYPE;
	}

	public JsonBusinessColumn getSelf() {
		return this;
	}
}
