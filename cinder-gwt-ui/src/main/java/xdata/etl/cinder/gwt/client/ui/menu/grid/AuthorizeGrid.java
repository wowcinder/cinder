/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.menu.grid;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;
import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.gridcolumn.AuthorizeColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.authorize.AuthorizeGroup;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class AuthorizeGrid extends Grid<Authorize> {

	public static List<ColumnConfig<Authorize, ?>> columns = new ArrayList<ColumnConfig<Authorize, ?>>();
	static {
		ColumnConfig<Authorize, AuthorizeGroup> group = AuthorizeColumnConfig
				.group();
		group.setCell(new SimpleSafeHtmlRenderer<AuthorizeGroup>() {
			@Override
			protected String _getLabel(AuthorizeGroup c) {
				return c.getName();
			}
		}.getCell());
		columns.add(group);
		ColumnConfig<Authorize, String> name = AuthorizeColumnConfig.name();
		columns.add(name);
	}

	private GwtCallBack<Authorize> callback;

	public AuthorizeGrid(GwtCallBack<Authorize> callback) {
		super(new ListStore<Authorize>(PropertyUtils.AuthorizeProperty.key()),
				new ColumnModel<Authorize>(columns));
		this.callback = callback;
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				AuthorizeGrid.this.mask("加载中...");
				RpcServiceUtils.AuthorizeRpcService
						.getAllocatenbeAuthorizes(new RpcAsyncCallback<List<Authorize>>() {
							@Override
							public void _onSuccess(List<Authorize> t) {
								getStore().addAll(t);
								AuthorizeGrid.this.unmask();
							}
						});
			}
		});
	}

	public GwtCallBack<Authorize> getCallback() {
		return callback;
	}

	public void setCallback(GwtCallBack<Authorize> callback) {
		this.callback = callback;
	}

}
