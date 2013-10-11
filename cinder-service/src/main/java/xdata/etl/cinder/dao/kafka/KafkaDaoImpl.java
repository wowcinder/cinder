/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.dao.kafka;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog.KafkaProcessServerStatus;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
@Repository
public class KafkaDaoImpl implements KafkaDao {

	@Resource(name = "cinderSf")
	private SessionFactory sf;

	Session getSession() {
		return sf.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getAllWatchDogIds() {
		String sql = "select id from {0} ";
		sql = MessageFormat.format(sql, KafkaWatchDog.class.getSimpleName());
		return getSession().createQuery(sql).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> getAllTopicSettingIds() {
		String sql = "select id from {0} ";
		sql = MessageFormat.format(sql,
				KafkaWatchDogTopicSetting.class.getSimpleName());
		return getSession().createQuery(sql).list();
	}

	@Override
	public Integer queryWatchDogIdByIp(String ip) {
		KafkaWatchDog dog = (KafkaWatchDog) getSession()
				.createCriteria(KafkaWatchDog.class)
				.add(Restrictions.eq("ip", ip)).uniqueResult();
		if (dog == null) {
			dog = new KafkaWatchDog();
			dog.setIp(ip);
			dog.setName(ip);
			dog.setStatus(KafkaProcessServerStatus.STARTING);
			getSession().persist(dog);
		} else {
			dog.setStatus(KafkaProcessServerStatus.STARTING);
			getSession().update(dog);
		}
		return dog.getId();
	}

	@Override
	public void updateRmiPort(Integer dogId, Integer rmiPort) {
		KafkaWatchDog dog = (KafkaWatchDog) getSession().load(
				KafkaWatchDog.class, dogId);
		dog.setRmiPort(rmiPort);
		getSession().update(dog);
	}
}
