/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@MappedSuperclass
public abstract class LogModelBase extends EntityHasTimeStampImpl {
	private static final long serialVersionUID = 2983067761331221658L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "mtype", updatable = false, length = 20)
	private LogModelType mtype;

	public Integer getId() {
		return id;
	}

	public LogModelType getMtype() {
		return mtype;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMtype(LogModelType mtype) {
		this.mtype = mtype;
	}

}
