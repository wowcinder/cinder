/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xdata.etl.cinder.logmodelmeta.shared.rmi.WatchDogManagerRMI;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Service
public class WatchDogManagerClientImpl implements WatchDogManagerClient {
	@Resource(name = "watchDogManagerRMI")
	private WatchDogManagerRMI watchDogManagerRMI;

	@Override
	public void tick() {
		watchDogManagerRMI.tick();
	}

	@Override
	public Integer login(Integer rmiPort) {
		return watchDogManagerRMI.login(rmiPort);
	}

	@Override
	public void logoff() {
		watchDogManagerRMI.logoff();
	}

	@Override
	public void reportTopicStatus(Set<Integer> aliveTopicIds) {
		// TODO Auto-generated method stub

	}
}
