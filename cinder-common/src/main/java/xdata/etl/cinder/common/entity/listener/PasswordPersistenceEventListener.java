/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.common.entity.listener;

import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;

import xdata.etl.cinder.common.entity.password.PasswordEncryptor;
import xdata.etl.cinder.common.entity.password.PasswordPersistence;

/**
 * @author XuehuiHe
 * @date 2013年9月6日
 */
public class PasswordPersistenceEventListener extends
		AbstractHibernateEventListener implements PreInsertEventListener,
		PostInsertEventListener, PreUpdateEventListener,
		PostUpdateEventListener {

	private static final long serialVersionUID = 1010463485417784456L;

	private PasswordEncryptor passwordEncryptor;

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		Object o = event.getEntity();
		if (o instanceof PasswordPersistence) {
			PasswordPersistence p = (PasswordPersistence) o;
			p.setPassword(null);
			setValue(event.getState(), event.getPersister()
					.getEntityMetamodel().getPropertyNames(),
					p.getPasswordPropertyName(), null, p);
		}
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		Object o = event.getEntity();
		if (o instanceof PasswordPersistence) {
			PasswordPersistence p = (PasswordPersistence) o;
			String password = null;
			if (p.getPasswrod() != null) {
				password = passwordEncryptor
						.getEncryptPassword(p.getPasswrod());
			} else {
				PasswordPersistence old = (PasswordPersistence) event
						.getPersister().getFactory().openSession()
						.get(p.getClass(), event.getId());
				password = old.getPasswrod();
			}
			p.setPassword(password);
			setValue(event.getState(), event.getPersister()
					.getEntityMetamodel().getPropertyNames(),
					p.getPasswordPropertyName(), password, p);
		}
		return false;
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		Object o = event.getEntity();
		if (o instanceof PasswordPersistence) {
			PasswordPersistence p = (PasswordPersistence) o;
			p.setPassword(null);
			setValue(event.getState(), event.getPersister()
					.getEntityMetamodel().getPropertyNames(),
					p.getPasswordPropertyName(), null, p);
		}
	}

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		Object o = event.getEntity();
		if (o instanceof PasswordPersistence) {
			PasswordPersistence p = (PasswordPersistence) o;
			if (p != null && p.getPasswrod() != null) {
				String password = passwordEncryptor.getEncryptPassword(p
						.getPasswrod());
				p.setPassword(password);
				setValue(event.getState(), event.getPersister()
						.getEntityMetamodel().getPropertyNames(),
						p.getPasswordPropertyName(), password, p);
			}
		}
		return false;
	}

	public PasswordEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}

	public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
		this.passwordEncryptor = passwordEncryptor;
	}

}
