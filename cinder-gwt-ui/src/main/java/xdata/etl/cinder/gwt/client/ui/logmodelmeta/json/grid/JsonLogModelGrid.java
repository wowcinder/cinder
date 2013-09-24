/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.grid;

import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.JsonLogModelColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModel;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class JsonLogModelGrid extends CinderGrid<JsonLogModel> {

	/**
	 * @param configProvider
	 * @param store
	 * @param gridConfig
	 */
	public JsonLogModelGrid(GridConfig gridConfig) {
		super(new GridConfigProvider<JsonLogModel>(new ListStore<JsonLogModel>(
				PropertyUtils.JsonLogModelProperty.key())) {

			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<JsonLogModel>> callback) {
				RpcServiceUtils.JsonLogModelMetaRpcService.pagingLogModel(
						loadConfig, callback);
			}

			@Override
			protected void initColumnConfig() {
				ColumnConfig<JsonLogModel, String> name = JsonLogModelColumnConfig
						.name();
				ColumnConfig<JsonLogModel, String> desc = JsonLogModelColumnConfig
						.desc();
				columns.add(name);
				columns.add(desc);
			}
		},  gridConfig);
	}

}
