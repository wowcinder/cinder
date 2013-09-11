/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.user.grid;

import java.util.Date;

import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.UserColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class UserGrid extends CinderGrid<User> {

	public static final DateTimeFormat DF = DateTimeFormat
			.getFormat("yyyy-MM-dd HH:mm:ss");

	public UserGrid() {
		super(new GridConfigProvider<User>() {

			@Override
			protected void initColumnConfig() {
				ColumnConfig<User, String> email = UserColumnConfig.email();
				ColumnConfig<User, UserGroup> userGroup = UserColumnConfig
						.userGroup();
				userGroup.setCell(new SimpleSafeHtmlRenderer<UserGroup>() {
					@Override
					protected String _getLabel(UserGroup c) {
						return c.getName();
					}
				}.getCell());
				ColumnConfig<User, Date> createTime = UserColumnConfig
						.createTime();
				createTime.setCell(new SimpleSafeHtmlRenderer<Date>() {
					@Override
					protected String _getLabel(Date c) {
						return DF.format(c);
					}
				}.getCell());
				columns.add(email);
				columns.add(userGroup);
				columns.add(createTime);
			}

			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<User>> callback) {
				RpcServiceUtils.UserRpcService.pagingUser(loadConfig, callback);
			}
		}, new ListStore<User>(PropertyUtils.UserProperty.key()));
	}
}
