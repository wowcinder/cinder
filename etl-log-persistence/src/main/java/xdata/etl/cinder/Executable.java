/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import xdata.etl.cinder.service.KafkaConsumerManager2;

/**
 * @author XuehuiHe
 * @date 2013年10月9日
 */
public class Executable {

	public static KafkaConsumerManager2 manager;

	public static void main(String[] args) throws InterruptedException,
			SchedulerException {
		ConfigUtil.init();
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-log-persistence.xml");
		manager = ctx.getBean(KafkaConsumerManager2.class);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				SchedulerFactoryBean schedulerFactoryBean = ctx
						.getBean(SchedulerFactoryBean.class);
				if (schedulerFactoryBean != null) {
					Scheduler scheduler = schedulerFactoryBean.getScheduler();
					try {
						scheduler.deleteJob("reportTopicStatus",
								Scheduler.DEFAULT_GROUP);
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
				if (manager != null) {
					try {
						manager.shutdown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				ctx.close();
			}
		});
		manager.run();
	}
}
