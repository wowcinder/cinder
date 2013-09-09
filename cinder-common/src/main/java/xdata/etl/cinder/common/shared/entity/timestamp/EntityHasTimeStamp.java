/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.common.shared.entity.timestamp;

import java.util.Date;

/**
 * @author XuehuiHe
 * @date 2013年9月5日
 */
public interface EntityHasTimeStamp {
	Date getLastUpdateTimeStamp();

	Date getCreateTime();

	void setCreateTime(Date createTime);

	void setLastUpdateTimeStamp(Date lastUpdateTimeStamp);

	String getCreateTimePropertyName();
}
