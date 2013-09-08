/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.lang.reflect.Method;
import java.util.List;

import xdata.etl.cinder.shared.entity.menu.MenuNode;

/**
 * 认证，授权服务
 * 
 * @author XuehuiHe
 * @date 2013年9月6日
 */
public interface AuthorizeService {
	boolean verify(Class<?> targetClass, Method invokeMethod);

	boolean login(String email, String password);

	void logout();

	boolean isLogin();

	boolean isAdmin();

	Integer getUserId();

	List<MenuNode> getUserMenus();
}
