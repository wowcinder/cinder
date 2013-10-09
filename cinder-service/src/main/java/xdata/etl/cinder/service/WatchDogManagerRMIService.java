/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.dao.WatchDogManagerRMIDao;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.rmi.WatchDogManagerRMI;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Service("watchdog_rmi_service")
@Transactional
public class WatchDogManagerRMIService implements WatchDogManagerRMI {
	@Autowired
	private WatchDogManagerRMIDao dao;

	public WatchDogManagerRMIService() {
	}

	@Override
	public Integer registerPrcessSever(Integer rmiPort) {
		String clientIp = getClientIp();
		KafkaWatchDog dog = dao.findWatchDog(clientIp, rmiPort);
		if (dog != null) {
			return dog.getId();
		}
		return null;
	}

	protected String getClientIp() {
		String clienthost;
		try {
			clienthost = RemoteServer.getClientHost();
			InetAddress ia = java.net.InetAddress.getByName(clienthost);
			return ia.getHostAddress();

		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void tick() {
		String clientIp = getClientIp();
		dao.refreshWatchDogAliveTime(clientIp);
	}
}
