/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.rmi;

import xdata.etl.cinder.logmodelmeta.shared.rmi.WatchDogManagerRMI;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public interface WatchDogManagerClient extends WatchDogManagerRMI {
	public void disabled();

	public void enabled();
}
