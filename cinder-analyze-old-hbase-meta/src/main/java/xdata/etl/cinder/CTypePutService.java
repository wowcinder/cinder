/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年10月14日
 */
@Service
@Transactional
public class CTypePutService {
	@Resource(name = "cinderSf")
	private SessionFactory sf;

	Session getSession() {
		return sf.getCurrentSession();
	}

	public HbaseTableVersion getHbaseTableVersion(String table) {
		HbaseTableVersion tableVersion = (HbaseTableVersion) getSession()
				.createCriteria(HbaseTableVersion.class)
				.createAlias("table", "table")
				.add(Restrictions.eq("table.name", table))
				.add(Restrictions.eq("version", "0.0")).uniqueResult();
		Hibernate.initialize(tableVersion.getColumns());
		return tableVersion;
	}
}
