/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.json;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Entity
public class LogModelColumnContentJson extends LogModelColumnContent {
	private static final long serialVersionUID = 7210341887275945510L;
	@Column(nullable = false, length = 150)
	@NotNull
	@Length(min = 1, max = 150)
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
