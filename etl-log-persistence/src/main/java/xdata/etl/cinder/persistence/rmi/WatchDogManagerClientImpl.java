/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.rmi;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xdata.etl.cinder.logmodelmeta.shared.rmi.WatchDogManagerRMI;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Service("watchDogManagerClient")
public class WatchDogManagerClientImpl implements WatchDogManagerClient {

	private final AtomicBoolean enabled;

	public WatchDogManagerClientImpl() {
		enabled = new AtomicBoolean(false);
	}

	@Resource(name = "watchDogManagerRMI")
	private WatchDogManagerRMI watchDogManagerRMI;

	@Override
	public synchronized void tick() {
		if (enabled.get()) {
			watchDogManagerRMI.tick();
		}
	}

	@Override
	public synchronized Integer login(Integer rmiPort) {
		return watchDogManagerRMI.login(rmiPort);
	}

	@Override
	public synchronized void logoff() {
		watchDogManagerRMI.logoff();
	}

	public synchronized void disabled() {
		enabled.set(false);
	}

	public synchronized void enabled() {
		enabled.set(true);
	}

}
