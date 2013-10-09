/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import xdata.etl.cinder.service.KafkaConsumerManager;

/**
 * @author XuehuiHe
 * @date 2013年10月9日
 */
public class Test {

	public static KafkaConsumerManager manager;

	public static void main(String[] args) throws InterruptedException {
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-cinder-xa.xml", "spring-log-persistence.xml");
		manager = ctx.getBean(KafkaConsumerManager.class);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (manager != null) {
					manager.shutdown();
				}
				ctx.close();
			}
		});
		manager.startAllConsumer();
	}
}
