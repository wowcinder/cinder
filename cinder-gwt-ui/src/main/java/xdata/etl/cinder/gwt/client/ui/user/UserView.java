/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.user;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;
import xdata.etl.cinder.gwt.client.common.event.EditEvent;
import xdata.etl.cinder.gwt.client.common.grid.HeaderBar;
import xdata.etl.cinder.gwt.client.ui.SimpleCenterView;
import xdata.etl.cinder.gwt.client.ui.user.editor.UserEditor;
import xdata.etl.cinder.gwt.client.ui.user.grid.UserGrid;
import xdata.etl.cinder.shared.annotations.MenuToken;
import xdata.etl.cinder.shared.entity.user.User;

import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@MenuToken(name = "用户管理", token = "user_manager", group = "用户管理")
public class UserView extends SimpleCenterView {

	private final HeaderBar headerBar;
	private final UserGrid grid;
	private final UserEditor editor;

	public UserView() {
		grid = new UserGrid();
		headerBar = new HeaderBar();
		editor = new UserEditor();
		add(headerBar, vd);
		add(grid, mainVd);
		add(grid.getPagingToolBar(), vd);

		headerBar.getAddBt().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				editor.fireEditEvent(new EditEvent<User>(new User(),
						new GwtCallBack<User>() {
							@Override
							protected void _call(User t) {
								getGrid().getStore().add(0, t);
							}
						}));
			}
		});

		headerBar.getDelBt().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				headerBar.getDelBt().disable();

				// TODO

				headerBar.getDelBt().enable();
			}
		});

		getGrid().addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				User u = getGrid().getStore().get(event.getRowIndex());
				getEditor().fireEditEvent(
						new EditEvent<User>(u, new GwtCallBack<User>() {

							@Override
							protected void _call(User t) {
								getGrid().getStore().commitChanges();
							}
						}));
			}
		});
	}

	public HeaderBar getHeaderBar() {
		return headerBar;
	}

	public UserGrid getGrid() {
		return grid;
	}

	public UserEditor getEditor() {
		return editor;
	}

}
