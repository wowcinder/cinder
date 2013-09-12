/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.c;

import javax.persistence.Column;
import javax.persistence.Entity;

import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Entity
public class LogModelVersionCType extends LogModelVersion {
	private static final long serialVersionUID = 1475531486090399441L;
	@Column(nullable = false)
	private Integer columnNum;

	public Integer getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(Integer columnNum) {
		this.columnNum = columnNum;
	}

}
