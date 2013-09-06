/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.common.entity.listener;

import java.util.Date;

import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.springframework.stereotype.Component;

import xdata.etl.cinder.common.entity.timestamp.EntityHasTimeStamp;

/**
 * @author XuehuiHe
 * @date 2013年9月5日
 */
@Component(value = "entityHasTimeStampListener")
public class EntityHasTimeStampListener extends AbstractHibernateEventListener
		implements PreInsertEventListener {

	private static final long serialVersionUID = 612228253431346177L;

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		Object o = event.getEntity();
		if (o instanceof EntityHasTimeStamp) {
			EntityHasTimeStamp entity = (EntityHasTimeStamp) o;
			Date now = new Date();
			entity.setCreateTime(now);
			setValue(event.getState(), event.getPersister()
					.getEntityMetamodel().getPropertyNames(),
					entity.getCreateTimePropertyName(), now, entity);
		}
		return false;
	}

}
