/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.hbase.htable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTableInterface;

import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;

/**
 * @author XuehuiHe
 * @date 2013年10月12日
 */
public interface HTableService {
	public void put(Map<String, List<HbaseRecord<String>>> recordMap)
			throws IOException;

	public void put(String tableName, List<HbaseRecord<String>> list)
			throws IOException;

	public void setHbaseConfig(Configuration hbaseConfig);

	public Configuration getHbaseConfig();

	public HTableInterface getHTable(String tableName) throws IOException;

	public void shutdown() throws IOException;
}
