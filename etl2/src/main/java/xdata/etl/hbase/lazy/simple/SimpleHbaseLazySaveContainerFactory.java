/**
 * 
 */
package xdata.etl.hbase.lazy.simple;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.hbase.lazy.IHbaseLazySaveContainer;
import xdata.etl.hbase.lazy.IHbaseLazySaveContainerFactory;

/**
 * @author Xuehuihe
 * 
 */
public class SimpleHbaseLazySaveContainerFactory implements
		IHbaseLazySaveContainerFactory {
	private int thresholdSize = 100;
	private int timeout = 10000;
	private int intervalMs = 1000;

	private HbaseContext hbaseCxt;

	public SimpleHbaseLazySaveContainerFactory() {
	}

	@Override
	public void setDefaultThresholdSize(int thresholdSize) {
		this.thresholdSize = thresholdSize;
	}

	@Override
	public int getDefaultThresholdSize() {
		return thresholdSize;
	}

	@Override
	public void setDefaultTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public int getDefaultTimeout() {
		return timeout;
	}

	@Override
	public void setDefaultIntervalMs(int intervalTime) {
		this.intervalMs = intervalTime;
	}

	@Override
	public int getDefaultIntervalMs() {
		return intervalMs;
	}

	public HbaseContext getHbaseCxt() {
		return hbaseCxt;
	}

	public void setHbaseCxt(HbaseContext hbaseCxt) {
		this.hbaseCxt = hbaseCxt;
	}

	@Override
	public IHbaseLazySaveContainer createLazySaveContainer() {
		return createLazySaveContainer(getDefaultThresholdSize(),
				getDefaultTimeout(), getDefaultIntervalMs());
	}

	@Override
	public IHbaseLazySaveContainer createLazySaveContainer(int thresholdSize,
			int timeout, int intervalMs) {
		IHbaseLazySaveContainer container = new SimpleHbaseLazySaveContainer(
				getHbaseCxt());
		container.setThresholdSize(thresholdSize);
		container.setIntervalMs(intervalMs);
		container.setTimeout(timeout);
		container.start();
		return container;
	}

}
