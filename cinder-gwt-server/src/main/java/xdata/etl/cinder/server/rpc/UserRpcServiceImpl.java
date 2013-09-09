/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.server.rpc;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import xdata.etl.cinder.gwt.client.service.UserRpcService;
import xdata.etl.cinder.service.UserService;
import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@Service
@AuthorizeGroupAnnotation("用户")
public class UserRpcServiceImpl implements UserRpcService {
	@Autowired
	private UserService userService;

	@Override
	@AuthorizeAnnotation("添加用户")
	public User saveUser(User user) {
		return userService.saveUser(user);
	}

	@Override
	@AuthorizeAnnotation("修改用户")
	public User updateUser(User user) {
		return userService.updateUser(user);
	}

	@Override
	@AuthorizeAnnotation("添加用户组")
	public UserGroup saveUserGroup(UserGroup userGroup) {
		return userService.saveUserGroup(userGroup);
	}

	@Override
	@AuthorizeAnnotation("修改用户组")
	public UserGroup updateUserGroup(UserGroup userGroup) {
		return userService.updateUserGroup(userGroup);
	}

	@Override
	@AuthorizeAnnotation("修改用户")
	public List<UserGroup> getUserGroupListForCombox() {
		return userService.getUserGroupListForCombox();
	}

	@Override
	@AuthorizeAnnotation("查询用户")
	public PagingLoadResult<User> paging(EtlPagingLoadConfigBean config)
			throws SharedException {
		return userService.paging(config);
	}

	@Override
	public Date dummyDate() {
		return null;
	}
}
