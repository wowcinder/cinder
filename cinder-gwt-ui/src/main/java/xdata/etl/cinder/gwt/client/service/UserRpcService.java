/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@RemoteServiceRelativePath("rpc/user.rpc")
public interface UserRpcService extends RemoteService {
	User saveUser(User user, String password) throws SharedException,
			ConstraintViolationException;

	User updateUser(User user, String password) throws SharedException,
			ConstraintViolationException;

	UserGroup saveUserGroup(UserGroup userGroup) throws SharedException,
			ConstraintViolationException;

	UserGroup updateUserGroup(UserGroup userGroup) throws SharedException,
			ConstraintViolationException;

	List<UserGroup> getUserGroupListForCombox() throws SharedException;

	void deleteUsers(List<Integer> ids) throws SharedException;

	void deleteUserGroups(List<Integer> ids) throws SharedException;

	PagingLoadResult<User> pagingUser(EtlPagingLoadConfigBean config)
			throws SharedException;

	PagingLoadResult<UserGroup> pagingUserGroup(EtlPagingLoadConfigBean config)
			throws SharedException;

	ValidationSupport dummy();
}
