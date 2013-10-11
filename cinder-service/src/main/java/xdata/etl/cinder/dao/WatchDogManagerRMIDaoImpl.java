/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog.KafkaProcessServerStatus;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus;

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

	@Override
	public void logoff(String clientIp) {
		KafkaWatchDog dog = (KafkaWatchDog) getSession()
				.createCriteria(KafkaWatchDog.class)
				.add(Restrictions.eq("ip", clientIp)).uniqueResult();
		if (dog != null) {
			dog.setStatus(KafkaProcessServerStatus.STOPED);
			getSession().update(dog);
		}
	}

	@Override
	public void refreshTopicStatus(String clientIp,
			Map<Integer, KafkaWatchDogTopicSettingStatus> topicToStatus) {
		@SuppressWarnings("unchecked")
		List<KafkaWatchDogTopicSetting> settings = getSession()
				.createCriteria(KafkaWatchDogTopicSetting.class)
				.createAlias("server", "server")
				.add(Restrictions.eq("server.ip", clientIp)).list();
		for (KafkaWatchDogTopicSetting kafkaWatchDogTopicSetting : settings) {
			kafkaWatchDogTopicSetting
					.setStatus(KafkaWatchDogTopicSettingStatus.STOPED);
			if (kafkaWatchDogTopicSetting.getTopic() != null
					&& topicToStatus.containsKey(kafkaWatchDogTopicSetting
							.getTopic().getId())) {
				kafkaWatchDogTopicSetting.setStatus(topicToStatus
						.get(kafkaWatchDogTopicSetting.getTopic().getId()));
			}
			getSession().update(kafkaWatchDogTopicSetting);
		}
	}

	@Override
	public void refreshWatchDogStatus(
			Set<String> liveIps,
			Map<String, Date> lastTickMap,
			Map<String, Map<Integer, KafkaWatchDogTopicSettingStatus>> topicStatus) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) - 30);
		long stamp = c.getTimeInMillis();
		for (String ip : liveIps) {
			boolean death = true;
			if (lastTickMap.containsKey(ip)
					&& lastTickMap.get(ip).getTime() >= stamp) {
				death = false;
			}
			KafkaWatchDog dog = (KafkaWatchDog) getSession()
					.createCriteria(KafkaWatchDog.class)
					.add(Restrictions.eq("ip", ip)).uniqueResult();
			if (dog != null) {
				dog.setStatus(death ? KafkaProcessServerStatus.EXCEPTION
						: KafkaProcessServerStatus.RUNNING);
				getSession().update(dog);
			}
		}
		for (Entry<String, Map<Integer, KafkaWatchDogTopicSettingStatus>> entry : topicStatus
				.entrySet()) {
			String ip = entry.getKey();
			Map<Integer, KafkaWatchDogTopicSettingStatus> topicToStatus = entry
					.getValue();
			@SuppressWarnings("unchecked")
			List<KafkaWatchDogTopicSetting> settings = getSession()
					.createCriteria(KafkaWatchDogTopicSetting.class)
					.createAlias("server", "server")
					.add(Restrictions.eq("server.ip", ip)).list();
			for (KafkaWatchDogTopicSetting kafkaWatchDogTopicSetting : settings) {
				kafkaWatchDogTopicSetting
						.setStatus(KafkaWatchDogTopicSettingStatus.EXCEPTION);
				if (kafkaWatchDogTopicSetting.getTopic() != null
						&& topicToStatus.containsKey(kafkaWatchDogTopicSetting
								.getTopic().getId())) {
					kafkaWatchDogTopicSetting.setStatus(topicToStatus
							.get(kafkaWatchDogTopicSetting.getTopic().getId()));
				}
				getSession().update(kafkaWatchDogTopicSetting);
			}

		}
	}

}
