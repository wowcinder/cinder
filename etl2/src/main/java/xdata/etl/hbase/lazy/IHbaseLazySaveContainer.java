package xdata.etl.hbase.lazy;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.hbase.exception.HbaseORMException;

/**
 * 延时保存缓存器, 缓存需要保存的数据,当时间超时，或缓存的数据的个数>=阀值时执行持久化存储
 * 
 * @author XuehuiHe
 * 
 */
public abstract class IHbaseLazySaveContainer extends Thread {
	public abstract void setThresholdSize(int thresholdSize);

	public abstract void setTimeout(int saveIntervalTime);

	public abstract void setIntervalMs(int checkIntervalTime);

	public abstract void setHbaseCxt(HbaseContext hbaseCxt);

	public abstract void lazySave(HbaseEntity entity) throws HbaseORMException;
}
