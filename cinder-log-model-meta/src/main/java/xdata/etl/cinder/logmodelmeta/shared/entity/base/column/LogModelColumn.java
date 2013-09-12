/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.base.column;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Entity
public class LogModelColumn extends LogModelSuperColumn {
	private static final long serialVersionUID = 8217488463310450909L;
	@ManyToOne(optional = false)
	@JoinColumn
	private LogModelVersion version;
	
	public LogModelVersion getVersion() {
		return version;
	}

	public void setVersion(LogModelVersion version) {
		this.version = version;
	}
}
