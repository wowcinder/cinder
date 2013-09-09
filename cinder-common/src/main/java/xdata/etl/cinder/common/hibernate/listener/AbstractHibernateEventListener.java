/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.common.hibernate.listener;

/**
 * @author XuehuiHe
 * @date 2013年9月6日
 */
public class AbstractHibernateEventListener {
	protected void setValue(Object[] currentState, String[] propertyNames,
			String propertyToSet, Object value, Object entity) {
		int index = 0;
		for (int i = 0; i < propertyNames.length; i++) {
			if (propertyToSet.equals(propertyNames[i])) {
				index = i;
				break;
			}
		}
		if (index >= 0) {
			currentState[index] = value;
		}
	}
}
