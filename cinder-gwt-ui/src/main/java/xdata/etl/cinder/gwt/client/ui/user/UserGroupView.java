/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.user;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.cinder.gwt.client.ui.AbstractCenterView;
import xdata.etl.cinder.gwt.client.ui.user.editor.UserGroupEditor;
import xdata.etl.cinder.gwt.client.ui.user.grid.UserGroupGrid;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.shared.annotations.MenuToken;
import xdata.etl.cinder.shared.entity.user.UserGroup;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@MenuToken(name = "用户组管理", token = "user_group_manager", group = "用户管理")
public class UserGroupView extends AbstractCenterView<UserGroup> {

	public UserGroupView() {
		super(new UserGroupGrid(), new UserGroupEditor());
	}

	@Override
	protected UserGroup newViewInstance() {
		return new UserGroup();
	}

	@Override
	protected void delete(List<UserGroup> list) {
		List<Integer> ids = new ArrayList<Integer>();
		for (UserGroup userGroup : list) {
			ids.add(userGroup.getId());
		}
		RpcServiceUtils.UserRpcService.deleteUserGroups(ids,
				getAsyncCallback(list));
	}

}
