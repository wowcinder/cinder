/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.c;

import javax.persistence.Column;
import javax.persistence.Entity;

import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Entity
public class LogModelColumnContentCType extends LogModelColumnContent {
	private static final long serialVersionUID = 7210341887275945510L;
	@Column(nullable = false)
	private Integer pos;

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

}
