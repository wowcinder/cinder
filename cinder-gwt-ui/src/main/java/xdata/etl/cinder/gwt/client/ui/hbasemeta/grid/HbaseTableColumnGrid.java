/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.hbasemeta.grid;

import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.HbaseTableColumnColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn.HbaseTableColumnType;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class HbaseTableColumnGrid extends CinderGrid<HbaseTableColumn> {

	/**
	 * @param configProvider
	 * @param store
	 * @param gridConfig
	 */
	public HbaseTableColumnGrid(GridConfig gridConfig) {
		super(new GridConfigProvider<HbaseTableColumn>() {

			@Override
			public void load(EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<HbaseTableColumn>> callback) {

			}

			@Override
			protected void initColumnConfig() {
				ColumnConfig<HbaseTableColumn, String> name = HbaseTableColumnColumnConfig
						.name();
				ColumnConfig<HbaseTableColumn, String> shortname = HbaseTableColumnColumnConfig
						.shortname();
				ColumnConfig<HbaseTableColumn, HbaseTableColumnType> type = HbaseTableColumnColumnConfig
						.type();
				type.setCell(new SimpleSafeHtmlRenderer<HbaseTableColumnType>() {

					@Override
					protected String _getLabel(HbaseTableColumnType c) {
						return c.name();
					}
				}.getCell());
				ColumnConfig<HbaseTableColumn, String> desc = HbaseTableColumnColumnConfig
						.desc();
				columns.add(name);
				columns.add(shortname);
				columns.add(type);
				columns.add(desc);
			}
		}, new ListStore<HbaseTableColumn>(
				PropertyUtils.HbaseTableColumnProperty.key()), gridConfig);
	}

}
