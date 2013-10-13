/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.hbase.lazy;

import java.io.IOException;

import xdata.etl.cinder.hbase.htable.HTableService;

/**
 * @author XuehuiHe
 * @date 2013年10月12日
 */
public interface LazyHTableService extends HTableService {
	public void timeout() throws IOException;

	public int getThresholdSize();

	public void setThresholdSize(int thresholdSize);

	public HTableService gethTableService();

	public void sethTableService(HTableService hTableService);

	public int getTimeout();

	public void setTimeout(int timeout);
}
