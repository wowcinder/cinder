/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.Tuple;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.TupleUtil;
import xdata.etl.cinder.common.entity.password.PasswordEncryptor;
import xdata.etl.cinder.dao.authorize.AuthorizeDao;
import xdata.etl.cinder.dao.user.UserDao;
import xdata.etl.cinder.shared.entity.user.User;

/**
 * 
 * @author XuehuiHe
 * @date 2013年9月6日
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {
	private static final String USER_ID_NAME_IN_SESSION = "USERID";
	private static final String AUTHORIZES_NAME_IN_SESSION = "AUTHORIZES";

	private boolean isDebug = false;

	@Autowired
	private UserDao userDao;
	@Autowired
	private AuthorizeDao authorizeDao;
	@Autowired
	private PasswordEncryptor passwordEncryptor;

	private final Map<Tuple, Set<String>> tupleToTokens;

	public AuthorizeServiceImpl() {
		tupleToTokens = new HashMap<Tuple, Set<String>>();
	}

	@Override
	public boolean verify(Class<?> targetClass, Method invokeMethod) {
		if (isDebug()) {
			return true;
		}
		if (isAdmin()) {
			return true;
		}
		Set<String> tokens = getTokens(targetClass, invokeMethod);
		Set<String> currUserAuthorizeTokens = getCurrUserAuthorizeTokens();
		for (String token : tokens) {
			if (currUserAuthorizeTokens.contains(token)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private Set<String> getCurrUserAuthorizeTokens() {
		return (Set<String>) getSession().getAttribute(
				AUTHORIZES_NAME_IN_SESSION);
	}

	@Override
	@Transactional(readOnly = true)
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
			session.setAttribute(AUTHORIZES_NAME_IN_SESSION,
					authorizeDao.getAuthorizeTokens(user.getId()));
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

	private Set<String> getTokens(Class<?> targetClass, Method invokeMethod) {
		Tuple tuple = new Tuple(targetClass, invokeMethod);
		if (!tupleToTokens.containsKey(tuple)) {
			initTokens(tuple);
		}
		return tupleToTokens.get(tuple);
	}

	private synchronized void initTokens(Tuple tuple) {
		if (tupleToTokens.containsKey(tuple)) {
			return;
		}
		tupleToTokens.put(tuple, TupleUtil.getTokens(tuple));
	}

	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

}
