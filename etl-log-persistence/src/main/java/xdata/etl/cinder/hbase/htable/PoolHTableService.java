package xdata.etl.cinder.hbase.htable;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;

public class PoolHTableService extends AbstractHTableService {

	private HTablePool pool;

	public PoolHTableService() {
	}

	@Override
	public HTableInterface getHTable(String tableName) throws IOException {
		return pool.getTable(tableName);
	}

	@Override
	public void setHbaseConfig(Configuration hbaseConfig) {
		super.setHbaseConfig(hbaseConfig);
		pool = new HTablePool(getHbaseConfig(), 10);
	}

	@Override
	public void shutdown() throws IOException {
		if (pool != null) {
			pool.close();
			pool = null;
		}
	}

}
