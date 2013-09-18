package xdata.etl.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanFactory implements BeanFactoryAware {
	private static BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		SpringBeanFactory.beanFactory = beanFactory;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> requiredType) {
		return (T) beanFactory.getBean(requiredType);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) beanFactory.getBean(beanName);
	}
}
