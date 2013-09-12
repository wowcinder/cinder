/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.grid;

import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.LogModelColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class LogModelGrid extends CinderGrid<LogModel> {

	/**
	 * @param configProvider
	 * @param store
	 * @param gridConfig
	 */
	public LogModelGrid(GridConfig gridConfig) {
		super(
				new GridConfigProvider<LogModel>() {

					@Override
					public void load(EtlPagingLoadConfigBean loadConfig,
							AsyncCallback<PagingLoadResult<LogModel>> callback) {
						RpcServiceUtils.LogModelMetaRpcService.pagingLogModel(
								loadConfig, callback);
					}

					@Override
					protected void initColumnConfig() {
						ColumnConfig<LogModel, String> name = LogModelColumnConfig
								.name();
						ColumnConfig<LogModel, String> desc = LogModelColumnConfig
								.desc();
						ColumnConfig<LogModel, LogModelType> mtype = LogModelColumnConfig
								.mtype();
						mtype.setCell(new SimpleSafeHtmlRenderer<LogModelType>() {
							@Override
							protected String _getLabel(LogModelType c) {
								return c.name();
							}
						}.getCell());
						columns.add(name);
						columns.add(desc);
						columns.add(mtype);
					}
				},
				new ListStore<LogModel>(PropertyUtils.LogModelProperty.key()),
				gridConfig);
	}

}
