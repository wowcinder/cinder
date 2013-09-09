/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.common.shared.entity.password;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;

import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStamp;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@MappedSuperclass
public class PasswordPersistenceImplHasTimeStamp extends
		PasswordPersistenceImpl implements Serializable, EntityHasTimeStamp {
	private static final long serialVersionUID = 3750991196000295395L;
	private Date createTime;
	private Date lastUpdateTimeStamp;

	public PasswordPersistenceImplHasTimeStamp() {
	}

	@Override
	@Temporal(TemporalType.TIMESTAMP)
	@AccessType("property")
	@Column(name = "ts", nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp")
	public Date getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}

	@Override
	@Temporal(TemporalType.TIMESTAMP)
	@AccessType("property")
	@Column(name = "create_time", nullable = false, updatable = false)
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public void setLastUpdateTimeStamp(Date lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}

	@Override
	@Transient
	public String getCreateTimePropertyName() {
		return "createTime";
	}
}
