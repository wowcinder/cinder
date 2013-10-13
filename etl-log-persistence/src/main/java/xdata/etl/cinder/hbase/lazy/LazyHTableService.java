/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.hbase.lazy;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import xdata.etl.cinder.hbase.htable.HTableService;
import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;

/**
 * @author XuehuiHe
 * @date 2013年10月12日
 */
public interface LazyHTableService {
	public void timeout() throws IOException;

	public void flush();

	public int getThresholdSize();

	public void setThresholdSize(int thresholdSize);

	public HTableService gethTableService();

	public void sethTableService(HTableService hTableService);

	public int getTimeout();

	public void setTimeout(int timeout);

	public void put(Map<String, List<HbaseRecord<String>>> recordMap)
			throws IOException;

	public void shutdown() throws IOException;

	public interface IHTableWorker {
		public void flush();

		public void timeout();

		public void put(List<HbaseRecord<String>> list);
	}
}
