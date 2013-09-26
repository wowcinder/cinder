/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import xdata.etl.cinder.service.LogModelTransformerManagerImpl.LogModelTransformerKey;
import xdata.etl.logmodel.transformer.LogModelTransformer;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
public interface LogModelTransformerManager {
	public LogModelTransformer<?> getTransformer(String model, String version);

	public LogModelTransformer<?> getTransformer(
			LogModelTransformerKey transformerKey);

	public void removeTransformer(String model, String version);

	public void removeTransformer(
			LogModelTransformerKey transformerKey);
}
