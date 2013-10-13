/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.hbase.lazy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.hadoop.hbase.client.HTableInterface;

import xdata.etl.cinder.hbase.htable.AbstractHTableService;
import xdata.etl.cinder.hbase.htable.HTableService;
import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;

/**
 * @author XuehuiHe
 * @date 2013年10月12日
 */
public class LazyHTableServiceImpl extends AbstractHTableService implements
		LazyHTableService {
	private int thresholdSize;
	private int timeout;
	private HTableService hTableService;

	private final ExecutorService executor;
	private final Map<String, HTableWorker> workerMap;

	public LazyHTableServiceImpl() {
		workerMap = new HashMap<String, LazyHTableServiceImpl.HTableWorker>();
		executor = Executors.newFixedThreadPool(20);
	}

	@Override
	public void put(Map<String, List<HbaseRecord<String>>> recordMap)
			throws IOException {
		for (Entry<String, List<HbaseRecord<String>>> entry : recordMap
				.entrySet()) {
			String tableName = entry.getKey();
			if (!workerMap.containsKey(tableName)) {
				createLazySaver(tableName);
			}
			final HTableWorker worker = workerMap.get(tableName);
			worker.put(entry.getValue());
		}
	}

	@Override
	public void put(final String tableName, final List<HbaseRecord<String>> list) {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					gethTableService().put(tableName, list);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private synchronized void createLazySaver(String tableName) {
		if (workerMap.containsKey(tableName)) {
			return;
		}
		workerMap.put(tableName, new HTableWorker(tableName));
	}

	public synchronized void timeout() throws IOException {
		for (Entry<String, HTableWorker> entry : workerMap.entrySet()) {
			entry.getValue().timeout();
		}
	}

	public synchronized void flush() throws IOException {
		for (Entry<String, HTableWorker> entry : workerMap.entrySet()) {
			entry.getValue().flush();
		}
	}

	public int getThresholdSize() {
		return thresholdSize;
	}

	public void setThresholdSize(int thresholdSize) {
		this.thresholdSize = thresholdSize;
	}

	public HTableService gethTableService() {
		return hTableService;
	}

	public void sethTableService(HTableService hTableService) {
		this.hTableService = hTableService;
	}

	@Override
	public HTableInterface getHTable(String tableName) throws IOException {
		return hTableService.getHTable(tableName);
	}

	@Override
	public void shutdown() throws IOException, InterruptedException {
		flush();
		executor.shutdown();
		while (!executor.isTerminated()) {
			TimeUnit.SECONDS.sleep(1);
		}
		hTableService.shutdown();
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public class HTableWorker {
		private final AtomicBoolean isSaving;
		private final AtomicLong total;
		private final AtomicLong lastSaveTime;
		private final String tableName;
		private final List<HbaseRecord<String>> pool;

		public HTableWorker(String tableName) {
			this.tableName = tableName;
			this.isSaving = new AtomicBoolean(false);
			this.lastSaveTime = new AtomicLong(System.currentTimeMillis());
			this.total = new AtomicLong(0);
			this.pool = Collections
					.synchronizedList(new ArrayList<HbaseRecord<String>>());
		}

		public void flush() {
			if (!tryLock()) {
				return;
			}
			List<HbaseRecord<String>> list = clonePool();
			unlock();
			LazyHTableServiceImpl.this.put(tableName, list);
		}

		public void timeout() {
			if (!tryLock()) {
				return;
			}
			if (!isTimeout()) {
				return;
			}
			List<HbaseRecord<String>> list = clonePool();
			unlock();
			LazyHTableServiceImpl.this.put(tableName, list);
		}

		protected List<HbaseRecord<String>> clonePool() {
			final List<HbaseRecord<String>> list = new ArrayList<HbaseRecord<String>>();
			synchronized (this) {
				list.addAll(pool);
				total.set(0);
				pool.clear();
				lastSaveTime.set(System.currentTimeMillis());
			}
			return list;
		}

		private boolean isTimeout() {
			return System.currentTimeMillis() - getTimeout() / 2 >= lastSaveTime
					.get();
		}

		private boolean isFull() {
			return total.get() >= thresholdSize;
		}

		public boolean tryLock() {
			return isSaving.compareAndSet(false, true);
		}

		public void unlock() {
			isSaving.set(false);
		}

		public void put(List<HbaseRecord<String>> list) {
			synchronized (this) {
				pool.addAll(list);
				total.addAndGet(list.size());
			}
			try {
				if (isFull()) {
					tryPut();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void tryPut() throws IOException {
			if (!tryLock()) {
				return;
			}
			if (!isFull()) {
				return;
			}
			List<HbaseRecord<String>> list = clonePool();
			unlock();
			LazyHTableServiceImpl.this.put(tableName, list);
		}
	}

}
