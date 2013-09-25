/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月25日
 */
@Transactional(readOnly = true)
@Service
public class JsonLogModelServiceImpl implements JsonLogModelService {
	@Resource(name = "cinderSf")
	private SessionFactory sf;

	@Override
	public JsonLogModelVersion getJsonLogModelVersion(String model,
			String version) {
		if (version == null || version.length() == 0) {
			version = "0.0";
		}
		JsonLogModelVersion jsonLogModelVersion = (JsonLogModelVersion) getSession()
				.createCriteria(JsonLogModelVersion.class)
				.add(Restrictions.eq("version", version))
				.createAlias("model", "model")
				.add(Restrictions.eq("model.name", model)).uniqueResult();
		initColumns(jsonLogModelVersion.getRootNode());
		return jsonLogModelVersion;
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

}
