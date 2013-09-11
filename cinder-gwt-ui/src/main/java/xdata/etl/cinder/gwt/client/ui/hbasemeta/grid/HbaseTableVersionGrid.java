/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.hbasemeta.grid;

import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.HbaseTableVersionColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class HbaseTableVersionGrid extends CinderGrid<HbaseTableVersion> {

	/**
	 * @param configProvider
	 * @param store
	 * @param gridConfig
	 */
	public HbaseTableVersionGrid(GridConfig gridConfig) {
		super(new GridConfigProvider<HbaseTableVersion>() {

			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<HbaseTableVersion>> callback) {
				RpcServiceUtils.HbaseMetaRpcService.pagingHbaseTableVersion(
						loadConfig, callback);
			}

			@Override
			protected void initColumnConfig() {
				ColumnConfig<HbaseTableVersion, String> version = HbaseTableVersionColumnConfig
						.version();
				ColumnConfig<HbaseTableVersion, HbaseTable> table = HbaseTableVersionColumnConfig
						.table();
				table.setCell(new SimpleSafeHtmlRenderer<HbaseTable>() {
					@Override
					protected String _getLabel(HbaseTable c) {
						return c.getName();
					}
				}.getCell());
				ColumnConfig<HbaseTableVersion, String> desc = HbaseTableVersionColumnConfig
						.desc();
				columns.add(table);
				columns.add(version);
				columns.add(desc);
			}
		}, new ListStore<HbaseTableVersion>(
				PropertyUtils.HbaseTableVersionProperty.key()), gridConfig);
	}
}
