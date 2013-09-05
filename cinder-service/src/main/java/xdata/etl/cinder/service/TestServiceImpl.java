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
	@Resource(name = "sessionFactory1")
	private SessionFactory sf1;
	@Resource(name = "sessionFactory2")
	private SessionFactory sf2;

	@Override
	@Transactional
	public void save() {

		Session s = sf1.openSession();

		Test1 test1 = new Test1();
		s.save(test1);

		System.out.println(test1.getCreateTime());
		
		Test2 test2 = new Test2();
		test2.setName("test2");
		
		sf2.getCurrentSession().save(test2);
		
//		throw new RuntimeException("test");

	}

}
