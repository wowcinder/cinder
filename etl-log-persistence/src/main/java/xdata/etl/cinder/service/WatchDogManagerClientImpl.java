/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xdata.etl.cinder.logmodelmeta.shared.entity.rmi.WatchDogManagerRMI;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Service
public class WatchDogManagerClientImpl implements WatchDogManagerClient {
	@Resource(name = "watchDogManagerRMI")
	private WatchDogManagerRMI watchDogManagerRMI;

	@Override
	public Integer registerPrcessSever(Integer rmiPort) {
		return watchDogManagerRMI.registerPrcessSever(rmiPort);
	}

	@Override
	public void tick() {
		watchDogManagerRMI.tick();
	}
}
