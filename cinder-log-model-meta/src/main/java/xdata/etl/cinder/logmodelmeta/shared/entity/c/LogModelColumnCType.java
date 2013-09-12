/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.c;

import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class LogModelColumnCType extends LogModelColumn {
	private static final long serialVersionUID = -257580929535613640L;

	public LogModelColumnCType() {
		setMtype(LogModelType.C_TYPE);
	}

	public LogModelColumnContentCType getContent() {
		return (LogModelColumnContentCType) super.getContent();
	}
}
