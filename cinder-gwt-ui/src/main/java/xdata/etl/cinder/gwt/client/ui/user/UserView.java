/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.user;

import xdata.etl.cinder.gwt.client.ui.SimpleCenterView;
import xdata.etl.cinder.gwt.client.ui.user.grid.UserGrid;
import xdata.etl.cinder.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@MenuToken(name = "用户管理", token = "user_manager", group = "用户管理")
public class UserView extends SimpleCenterView {
	public UserView() {
		// ContentPanel cp = new ContentPanel();
		// cp.setHeight(200);
		// cp.setWidth(300);
		//
		UserGrid grid = new UserGrid();
		//
		// cp.setWidget(grid);
		add(grid);

	}
}
