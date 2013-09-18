package xdata.etl.hbase.lazy.simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.hbase.exception.HbaseORMException;
import xdata.etl.hbase.lazy.IHbaseLazySaveContainer;
import xdata.etl.hbase.transform.HbaseTransformer.HbaseTransformResult;
import xdata.etl.hbase.transform.HbaseTransformer.HbaseTransformResultCollection;

public class SimpleHbaseLazySaveContainer extends IHbaseLazySaveContainer {

	private static Logger logger = LoggerFactory
			.getLogger(SimpleHbaseLazySaveContainer.class);

	private int thresholdSize;
	private int timeout;
	private int intervalMs;
	private Long lastSaveTime;
	private final HTablePool pool;

	private HbaseContext hbaseCxt;
	private final List<HbaseTransformResult> results;

	public SimpleHbaseLazySaveContainer(HbaseContext hbaseCxt) {
		setHbaseCxt(hbaseCxt);
		setLastSaveTime(System.currentTimeMillis());
		pool = new HTablePool(getHbaseCxt().getCfg(), 10);
		results = Collections
				.synchronizedList(new ArrayList<HbaseTransformResult>());
	}

	protected boolean isReadyDoSave() {
		return System.currentTimeMillis() - getLastSaveTime() >= getTimeOut()
				|| results.size() >= getThresholdSize();
	}

	protected void saveImmediate() throws IOException {
		if (!isReadyDoSave()) {
			return;
		}
		List<HbaseTransformResult> storePuts = new ArrayList<HbaseTransformResult>();
		synchronized (results) {
			storePuts.addAll(results);
			results.clear();
			setLastSaveTime(System.currentTimeMillis());
		}
		HbaseTransformResultCollection c = new HbaseTransformResultCollection();
		for (HbaseTransformResult hbaseTransformResult : storePuts) {
			c.merge(hbaseTransformResult);
		}
		Map<String, List<Put>> map = c.getPutsMap();
		Set<String> tableNames = map.keySet();
		for (String tableName : tableNames) {
			HTableInterface htable = pool.getTable(tableName);
			htable.put(map.get(tableName));
			htable.close();
		}
	}

	@Override
	public void run() {
		while (true) {
			if (Thread.interrupted()) {
				shutdown();
				break;
			}
			try {
				saveImmediate();
			} catch (IOException e1) {
				logger.error("Lazy save Exception", e1);
			}
			try {
				TimeUnit.MILLISECONDS.sleep(getIntervalMs());
			} catch (InterruptedException e) {
				shutdown();
				break;
			}
		}
	}

	protected void shutdown() {
		logger.info("IHbaseLazySaveContainer shutdowning");
		try {
			saveImmediate();
			pool.close();
		} catch (IOException e1) {
			logger.error("Lazy save Exception", e1);
		}
	}

	@Override
	public void lazySave(HbaseEntity entity) throws HbaseORMException {
		this.results.add(getHbaseCxt().getTransformer()
				.transform(entity, false));
	}

	public int getThresholdSize() {
		return thresholdSize;
	}

	public int getTimeOut() {
		return timeout;
	}

	public Long getLastSaveTime() {
		return lastSaveTime;
	}

	public HbaseContext getHbaseCxt() {
		return hbaseCxt;
	}

	public int getIntervalMs() {
		return intervalMs;
	}

	public void setThresholdSize(int thresholdSize) {
		this.thresholdSize = thresholdSize;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setLastSaveTime(Long lastStoreTime) {
		this.lastSaveTime = lastStoreTime;
	}

	public void setHbaseCxt(HbaseContext hbaseCxt) {
		this.hbaseCxt = hbaseCxt;
	}

	public void setIntervalMs(int intervalMs) {
		this.intervalMs = intervalMs;
	}
}
