package xdata.etl.cinder.hbase.htable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.logmodel.transformer.TypeParser;

public abstract class AbstractHTableService implements HTableService {
	private Configuration hbaseConfig;

	@Override
	public void put(Map<String, List<HbaseRecord<String>>> recordMap)
			throws IOException {
		for (Entry<String, List<HbaseRecord<String>>> entry : recordMap
				.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}

	}

	@Override
	public void put(String tableName, List<HbaseRecord<String>> list)
			throws IOException {
		HTableInterface htable = getHTable(tableName);
		htable.put(generatePuts(list));
		htable.flushCommits();
		htable.close();

	}

	public Configuration getHbaseConfig() {
		return hbaseConfig;
	}

	public void setHbaseConfig(Configuration hbaseConfig) {
		this.hbaseConfig = hbaseConfig;
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
