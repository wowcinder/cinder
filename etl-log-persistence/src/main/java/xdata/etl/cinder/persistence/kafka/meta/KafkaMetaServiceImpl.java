/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.kafka.meta;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

/**
 * @author XuehuiHe
 * @date 2013年9月25日
 */
@Transactional(readOnly = true)
@Service
public class KafkaMetaServiceImpl implements KafkaMetaService {
	@Resource(name = "cinderSf")
	private SessionFactory sf;

	@Override
	public LogModelVersion<?> getLogModelVersion(String model, String version) {
		if (version == null || version.length() == 0) {
			version = "0.0";
		}
		LogModelVersion<?> logModelVersion = (LogModelVersion<?>) getSession()
				.createCriteria(LogModelVersion.class)
				.add(Restrictions.eq("version", version))
				.createAlias("model", "model")
				.add(Restrictions.eq("model.name", model)).uniqueResult();
		if (logModelVersion instanceof JsonLogModelVersion) {
			initColumns(((JsonLogModelVersion) logModelVersion).getRootNode());
		} else {
			initColumns(((CTypeLogModelVersion) logModelVersion).getRootNode());
		}
		return logModelVersion;
	}

	private void initColumns(CTypeLogModelGroupColumn groupColumn) {
		if (Hibernate.isInitialized(groupColumn.getColumns())) {
			Hibernate.initialize(groupColumn.getColumns());
		}
		if (groupColumn.getColumns() != null) {
			for (CTypeLogModelColumn column : groupColumn.getColumns()) {
				if (column instanceof CTypeLogModelGroupColumn) {
					initColumns((CTypeLogModelGroupColumn) column);
				}
			}
		}
	}

	private void initColumns(JsonLogModelGroupColumn groupColumn) {
		if (Hibernate.isInitialized(groupColumn.getColumns())) {
			Hibernate.initialize(groupColumn.getColumns());
		}
		if (groupColumn.getColumns() != null) {
			for (JsonLogModelColumn column : groupColumn.getColumns()) {
				if (column instanceof JsonLogModelGroupColumn) {
					initColumns((JsonLogModelGroupColumn) column);
				}
			}
		}
	}

	Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public KafkaWatchDog findWatchDog(Integer id) {
		return (KafkaWatchDog) getSession().get(KafkaWatchDog.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KafkaWatchDogTopicSetting> getAllTopicSettings(KafkaWatchDog dog) {
		return getSession().createCriteria(KafkaWatchDogTopicSetting.class)
				.add(Restrictions.eq("server", dog))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

}
