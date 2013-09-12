/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.base.column;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelBase;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class LogModelSuperColumn extends LogModelBase {
	private static final long serialVersionUID = 9032934534578162445L;
	@OneToOne
	@JoinColumn
	private LogModelColumnContent content;

	public LogModelColumnContent getContent() {
		return content;
	}

	public void setContent(LogModelColumnContent content) {
		this.content = content;
	}

}
