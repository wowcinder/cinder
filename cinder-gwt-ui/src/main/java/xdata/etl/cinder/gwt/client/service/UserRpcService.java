/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.service;

import java.util.Date;
import java.util.List;

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
	User saveUser(User user);

	User updateUser(User user);

	UserGroup saveUserGroup(UserGroup userGroup);

	UserGroup updateUserGroup(UserGroup userGroup);

	List<UserGroup> getUserGroupListForCombox();

	PagingLoadResult<User> paging(EtlPagingLoadConfigBean config)
			throws SharedException;
	
	Date dummyDate();
	
}
