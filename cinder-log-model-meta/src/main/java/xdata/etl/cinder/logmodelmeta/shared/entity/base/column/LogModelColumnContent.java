/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.base.column;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelBase;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LogModelColumnContent extends LogModelBase {
	private static final long serialVersionUID = 5933324523092992440L;

}
