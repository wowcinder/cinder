/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog.KafkaProcessServerStatus;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Repository
public class WatchDogManagerRMIDaoImpl implements WatchDogManagerRMIDao {
	@Resource(name = "cinderSf")
	private SessionFactory sf;

	Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public KafkaWatchDog findWatchDog(String ip, Integer rmiPort) {
		KafkaWatchDog dog = (KafkaWatchDog) getSession()
				.createCriteria(KafkaWatchDog.class)
				.add(Restrictions.eq("ip", ip)).uniqueResult();
		if (dog == null) {
			dog = new KafkaWatchDog();
			dog.setIp(ip);
			dog.setName(ip);
			dog.setRmiPort(rmiPort);
			dog.setStatus(KafkaProcessServerStatus.STARTING);
			getSession().persist(dog);
		} else {
			dog.setStatus(KafkaProcessServerStatus.STARTING);
			dog.setRmiPort(rmiPort);
			getSession().update(dog);
		}
		return dog;
	}

	@Override
	public void refreshWatchDogAliveTime(String ip) {
		KafkaWatchDog dog = (KafkaWatchDog) getSession()
				.createCriteria(KafkaWatchDog.class)
				.add(Restrictions.eq("ip", ip)).uniqueResult();
		if (dog != null) {
			dog.setStatus(KafkaProcessServerStatus.RUNNING);
			getSession().update(dog);
		}
	}

}
