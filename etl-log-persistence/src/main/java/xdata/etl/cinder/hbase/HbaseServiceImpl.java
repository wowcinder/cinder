/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.logmodel.transformer.TypeParser;

/**
 * @author XuehuiHe
 * @date 2013年10月12日
 */
@Service
public class HbaseServiceImpl implements HbaseService {
	private final Configuration cfg;

	public HbaseServiceImpl() {
		cfg = HBaseConfiguration.create();
	}

	public Configuration getCfg() {
		return cfg;
	}

	@Override
	public void save(Map<String, List<HbaseRecord<String>>> recordMap)
			throws IOException {
		for (Entry<String, List<HbaseRecord<String>>> entry : recordMap
				.entrySet()) {
			String tableName = entry.getKey();
			HTable htable = new HTable(getCfg(), tableName);
			htable.put(generatePuts(entry.getValue()));
			htable.flushCommits();
			htable.close();
		}
	}

	protected List<Put> generatePuts(List<HbaseRecord<String>> records) {
		List<Put> puts = new ArrayList<Put>();
		for (HbaseRecord<String> record : records) {
			Put put = new Put(Bytes.toBytes(record.getKey()));
			for (Entry<String, Object> entry : record.getData().entrySet()) {
				put.add(Bytes.toBytes("d"), Bytes.toBytes(entry.getKey()),
						getValueBytes(entry.getValue()));
			}
			puts.add(put);
		}
		return puts;
	}

	protected byte[] getValueBytes(Object v) {
		return TypeParser.toBytes(v);
	}
}
