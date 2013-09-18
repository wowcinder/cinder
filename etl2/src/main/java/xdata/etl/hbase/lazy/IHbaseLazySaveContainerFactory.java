package xdata.etl.hbase.lazy;

import xdata.etl.hbase.HbaseContext;

/**
 * 延时保存容器工厂
 * 
 * @author XuehuiHe
 * 
 */
public interface IHbaseLazySaveContainerFactory {
	/**
	 * 默认缓存数据个数的阀值
	 * 
	 * @param thresholdSize
	 */
	public void setDefaultThresholdSize(int thresholdSize);

	public int getDefaultThresholdSize();

	/**
	 * 默认缓存数据超时时间(毫秒)
	 * 
	 * @param timeout
	 */
	public void setDefaultTimeout(int timeout);

	public int getDefaultTimeout();

	/**
	 * 默认缓存数据检测的interval时间(毫秒)
	 * 
	 * @param intervalMs
	 */
	public void setDefaultIntervalMs(int intervalMs);

	public int getDefaultIntervalMs();
	
	public void setHbaseCxt(HbaseContext hbaseCxt);

	/**
	 * 获得默认值的延时保存容器
	 * 
	 * @return
	 */
	public IHbaseLazySaveContainer createLazySaveContainer();

	public IHbaseLazySaveContainer createLazySaveContainer(int thresholdSize,
			int timeout, int intervalMs);
}
