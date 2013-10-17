package xdata.etl.cinder.hbase.htable;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;

public class PoolHTableService extends AbstractHTableService {

	private HTablePool pool;

	public PoolHTableService() {
	}

	public PoolHTableService(Configuration hbaseConfig, int poolSize) {
		setHbaseConfig(hbaseConfig);
		pool = new HTablePool(getHbaseConfig(), poolSize);
	}

	@Override
	public HTableInterface getHTable(String tableName) throws IOException {
		return pool.getTable(tableName);
	}

	@Override
//	@PreDestroy
	public void shutdown() throws IOException {
//		if (pool != null) {
//			pool.close();
//			pool = null;
//		}
	}

}
