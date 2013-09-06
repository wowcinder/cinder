/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xdata.etl.cinder.common.entity.password.PasswordEncryptor;
import xdata.etl.cinder.dao.user.UserDao;
import xdata.etl.cinder.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年9月6日
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {
	private static final String USER_ID_NAME_IN_SESSION = "userId";

	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncryptor passwordEncryptor;

	@Override
	public boolean verify(Class<?> targetClass, Method invokeMethod) {
		// TODO
		return false;
	}

	@Override
	public boolean login(String email, String password) {
		if (isLogin()) {
			logout();
		}
		if (password == null) {
			password = "";
		}
		String encryptPassword = passwordEncryptor.getEncryptPassword(password);
		User user = userDao.findUser(email, encryptPassword);
		if (user != null) {
			HttpSession session = getSession();
			session.setAttribute(USER_ID_NAME_IN_SESSION, user.getId());
			// TODO 授权
			return true;
		}
		return false;
	}

	@Override
	public void logout() {
		HttpSession session = getSession();
		@SuppressWarnings({ "unchecked" })
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			session.removeAttribute(name);
		}
	}

	@Override
	public boolean isLogin() {
		Integer uid = getUserId();
		if (uid != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAdmin() {
		Integer uid = getUserId();
		if (uid != null && uid == 0) {
			return true;
		}
		return false;
	}

	@Override
	public Integer getUserId() {
		return (Integer) getSession().getAttribute(USER_ID_NAME_IN_SESSION);
	}

	protected HttpSession getSession() {
		if (RequestContextHolder.currentRequestAttributes() != null) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			return attr.getRequest().getSession();
		}
		return null;
	}
}
