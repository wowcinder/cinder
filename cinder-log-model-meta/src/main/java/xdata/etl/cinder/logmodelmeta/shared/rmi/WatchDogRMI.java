/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.rmi;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * WatchDog 接收的RMI
 * 
 * @author XuehuiHe
 * @date 2013年9月26日
 */
public interface WatchDogRMI {
	public void restart();

	public AtomicBoolean getIsRunning();
}
