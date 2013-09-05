/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.common.entity.listener;

import java.util.Date;

import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;

import xdata.etl.cinder.common.entity.EntityHasTimeStamp;

/**
 * @author XuehuiHe
 * @date 2013年9月5日
 */
public class EntityHasTimeStampListener implements PreInsertEventListener {

	private static final long serialVersionUID = 612228253431346177L;

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		Object o = event.getEntity();
		if (o instanceof EntityHasTimeStamp) {
			EntityHasTimeStamp entity = (EntityHasTimeStamp) o;
			Date now = new Date();

			String[] propertyNames = event.getPersister().getEntityMetamodel()
					.getPropertyNames();
			Object[] state = event.getState();
			
			entity.setCreateTime(now);
			setValue(state, propertyNames, entity.getCreateTimePropertyName(),
					now, entity);
		}
		return false;
	}

	void setValue(Object[] currentState, String[] propertyNames,
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
