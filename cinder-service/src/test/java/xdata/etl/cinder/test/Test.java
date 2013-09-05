/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xdata.etl.cinder.service.TestService;

/**
 * @author XuehuiHe
 * @date 2013年9月5日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-service.xml" })
public class Test {
	@Autowired
	public TestService service;

	@org.junit.Test
	public void test() {
		service.save();
	}
}
