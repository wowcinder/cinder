/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.entity.Test1;
import xdata.etl.cinder.entity.Test2;

/**
 * @author XuehuiHe
 * @date 2013年9月5日
 */
@Service
public class TestServiceImpl implements TestService {
	@Resource(name = "cinderSf")
	private SessionFactory sf;

	@Override
	@Transactional
	public void save() {

		Session s = sf.getCurrentSession();
		Test1 test1 = new Test1();
		s.save(test1);

		Test2 test2 = new Test2();
		test2.setName("test2");

		s.save(test2);

	}

}
