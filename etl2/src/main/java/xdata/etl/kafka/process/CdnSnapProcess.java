package xdata.etl.kafka.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xdata.etl.entity.cdn.CdnCacheInfo;
import xdata.etl.entity.cdn.CdnCacheSnapNotify;
import xdata.etl.hbase.exception.HbaseORMException;
import xdata.etl.hbase.lazy.IHbaseLazySaveContainer;
import xdata.etl.kafka.consumer.SimpleConsumer;
import xdata.etl.kafka.exception.KafkaTopicProcessException;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

public class CdnSnapProcess extends AbstractStartWithStampSnapProcess {
	public static final Logger logger = LoggerFactory
			.getLogger(SimpleConsumer.class);
	private Date targetDate;

	public CdnSnapProcess() {
	}

	@Override
	protected void deal(KafkaTransformResult result)
			throws KafkaTopicProcessException {
		if (result.get().size() == 0 || result.get().iterator().next() == null) {
			throw new KafkaTopicProcessException(
					"cdn_cache_snap_notify is empty");
		}
		CdnCacheSnapNotify notify = CdnCacheSnapNotify.class.cast(result.get()
				.iterator().next());
		String filePath = getKafkaCtx().getCdnCacheSnapFolderPath()
				+ File.separator + notify.getFileName();
		logger.info(filePath + " msg reach");
		File file = new File(filePath);
		if (!file.exists()) {
			throw new KafkaTopicProcessException(filePath + " do not exists");
		}
		if (!file.canRead()) {
			throw new KafkaTopicProcessException(filePath + " can not read");
		}
		deal(file, notify.getIp());
	}

	protected void deal(File file, String ip) {
		CdnCacheSnapFileProcess fileProcess = new CdnCacheSnapFileProcess(
				this.getTargetDate(), ip);
		int threadCount = getKafkaCtx().getCdnCacheSnapProcessThread();
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		Collection<Future<?>> futures = new LinkedList<Future<?>>();
		BufferedReader read = null;
		try {
			read = new BufferedReader(new FileReader(file));
			for (int i = 0; i < threadCount; i++) {
				futures.add(executor.submit(fileProcess.getRunnable(read)));
			}
			for (Future<?> future : futures) {
				future.get();
			}
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			file.delete();
		} catch (FileNotFoundException e) {
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			executor.shutdown();
			fileProcess.shutdown();
			logger.info(file.getAbsolutePath() + " deal ended");
		}
	}

	public Date getTargetDate() {
		if (this.targetDate != null) {
			return this.targetDate;
		}
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
		return c.getTime();
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	class CdnCacheSnapFileProcess {
		private IHbaseLazySaveContainer lazySaveContainer;
		private CdnCacheInfoTransformer transformer;

		public CdnCacheSnapFileProcess(Date targetDate, String ip) {
			this.transformer = new CdnCacheInfoTransformer(targetDate, ip);
			this.lazySaveContainer = getHbaseCtx()
					.getLazySaveContainerFactory().createLazySaveContainer(500,
							10000, 1000);
		}

		public Runnable getRunnable(final BufferedReader read) {
			return new Runnable() {
				@Override
				public void run() {
					String line = null;
					while ((line = getLine()) != null) {
						try {
							CdnCacheInfo info = getTransformer()
									.transform(line);
							getLazySaveContainer().lazySave(info);
							logger.debug("CDN_CACHE_SNAP\t" + line);
						} catch (HbaseORMException e) {
							e.printStackTrace();
						} catch (RuntimeException e) {
							logger.error("line:" + line
									+ " ,cdn snap transform exception", e);
						}
					}
				}

				private String getLine() {
					synchronized (read) {
						try {
							return read.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					return null;
				}
			};
		}

		public IHbaseLazySaveContainer getLazySaveContainer() {
			return lazySaveContainer;
		}

		public CdnCacheInfoTransformer getTransformer() {
			return transformer;
		}

		public void shutdown() {
			getLazySaveContainer().interrupt();
		}
	}

	class CdnCacheInfoTransformer {
		private int index;
		private String dayStr;
		private String ip;

		public CdnCacheInfoTransformer(Date targetDate, String ip) {
			this.index = calculateIndex(targetDate);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			this.dayStr = df.format(targetDate);
			this.ip = ip;
		}

		private int calculateIndex(Date targetDate) {
			// 时区为+8区
			long ms = targetDate.getTime() + 1000 * 60 * 60 * 8;
			double index = ms / (1000 * 60 * 60 * 24.0);
			double index2 = Math.ceil(ms / (1000 * 60 * 60 * 24.0));
			if (index == index2) {
				return ((int) index2) % 30;
			} else {
				return (int) index2 % 30 - 1;
			}
		}

		public CdnCacheInfo transform(String line) {
			String[] items = line.split(",");
			CdnCacheInfo info = new CdnCacheInfo();
			info.setFid(items[0].substring(0, 32));
			info.setBlockId(items[0].substring(32));
			info.setAccessTimes(Long.parseLong(items[this.index + 2]));
			info.setBlockSize(Long.parseLong(items[items.length - 1]));
			info.setDayStr(this.dayStr);
			info.setIp(this.ip);
			return info;
		}
	}
}
