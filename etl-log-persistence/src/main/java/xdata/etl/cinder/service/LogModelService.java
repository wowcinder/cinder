/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月25日
 */
public interface LogModelService {
	LogModelVersion<?> getLogModelVersion(String model, String version);
}
