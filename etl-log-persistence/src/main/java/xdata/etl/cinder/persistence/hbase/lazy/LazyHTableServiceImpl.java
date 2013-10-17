/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.hbase.lazy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.persistence.hbase.htable.HTableService;

/**
 * @author XuehuiHe
 * @date 2013年10月12日
 */
public class LazyHTableServiceImpl implements LazyHTableService {
	private static Logger logger = LoggerFactory
			.getLogger(LazyHTableService.class);

	private int thresholdSize;
	private int timeout;
	private HTableService hTableService;

	private final ExecutorService executor;
	private final Map<String, IHTableWorker> workerMap;

	public LazyHTableServiceImpl() {
		workerMap = new HashMap<String, LazyHTableServiceImpl.IHTableWorker>();
		executor = Executors.newFixedThreadPool(20);
		logger.info("create lazy service");
	}

	@Override
	public void put(Map<String, List<HbaseRecord<String>>> recordMap)
			throws IOException {
		for (Entry<String, List<HbaseRecord<String>>> entry : recordMap
				.entrySet()) {
			String tableName = entry.getKey();
			if (!workerMap.containsKey(tableName)) {
				createLazyWorker(tableName);
			}
			final IHTableWorker worker = workerMap.get(tableName);
			worker.put(entry.getValue());
		}
	}

	protected void put(final String tableName,
			final List<HbaseRecord<String>> list) {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					gethTableService().put(tableName, list);
				} catch (IOException e) {
					logger.warn("hTableService can't work");
					e.printStackTrace();
				}
			}
		});
	}

	private synchronized void createLazyWorker(String tableName) {
		if (workerMap.containsKey(tableName)) {
			return;
		}
		logger.debug("create lazy worker:" + tableName);
		workerMap.put(tableName, new HTableWorker(tableName));
	}

	public synchronized void timeout() throws IOException {
		logger.debug("lazy htable service timeout");
		for (Entry<String, IHTableWorker> entry : workerMap.entrySet()) {
			entry.getValue().timeout();
		}
	}

	public synchronized void flush() {
		logger.debug("lazy htable service flush");
		for (Entry<String, IHTableWorker> entry : workerMap.entrySet()) {
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
	@PreDestroy
	public void shutdown() throws IOException {
		logger.info("lazy htable service shutdown...");
		flush();
		executor.shutdown();
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public class HTableWorker implements IHTableWorker {
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
				logger.info("lazy worker :" + tableName
						+ " flush,but can not get lock");
				return;
			}
			if (total.get() == 0) {
				logger.info("lazy worker :" + tableName
						+ " flush,but total is 0");
				unlock();
				return;
			}
			logger.info("lazy worker :" + tableName + " flush");
			List<HbaseRecord<String>> list = clonePool();
			unlock();
			LazyHTableServiceImpl.this.put(tableName, list);
		}

		public void timeout() {
			if (!tryLock()) {
				logger.debug("lazy worker :" + tableName
						+ " timeout,but can not get lock");
				return;
			}
			if (!isTimeout() || total.get() == 0) {
				logger.debug("lazy worker :" + tableName
						+ " timeout,but just put either total is 0");
				unlock();
				return;
			}
			logger.debug("lazy worker :" + tableName + " timeout");
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

		protected boolean isTimeout() {
			return System.currentTimeMillis() - getTimeout() / 2 >= lastSaveTime
					.get();
		}

		protected boolean isFull() {
			return total.get() >= thresholdSize;
		}

		protected boolean tryLock() {
			return isSaving.compareAndSet(false, true);
		}

		protected void unlock() {
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

		protected void tryPut() throws IOException {
			if (!tryLock()) {
				logger.debug("lazy worker :" + tableName
						+ " try put ,but can not get lock");
				return;
			}
			if (!isFull()) {
				logger.debug("lazy worker :" + tableName
						+ " try put ,but list is not full");
				unlock();
				return;
			}
			logger.debug("lazy worker :" + tableName + " putting");
			List<HbaseRecord<String>> list = clonePool();
			unlock();
			LazyHTableServiceImpl.this.put(tableName, list);
		}
	}

}
