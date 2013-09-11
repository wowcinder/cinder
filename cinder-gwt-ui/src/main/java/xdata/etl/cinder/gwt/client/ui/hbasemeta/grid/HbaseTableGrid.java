package xdata.etl.cinder.gwt.client.ui.hbasemeta.grid;

import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.HbaseTableColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public class HbaseTableGrid extends CinderGrid<HbaseTable> {

	public HbaseTableGrid(GridConfig gridConfig) {
		super(
				new GridConfigProvider<HbaseTable>() {

					@Override
					public void load(EtlPagingLoadConfigBean loadConfig,
							AsyncCallback<PagingLoadResult<HbaseTable>> callback) {
						RpcServiceUtils.HbaseMetaRpcService.pagingHbaseTable(
								loadConfig, callback);
					}

					@Override
					protected void initColumnConfig() {
						ColumnConfig<HbaseTable, String> name = HbaseTableColumnConfig
								.name();
						ColumnConfig<HbaseTable, String> shortname = HbaseTableColumnConfig
								.shortname();
						ColumnConfig<HbaseTable, String> desc = HbaseTableColumnConfig
								.desc();

						columns.add(name);
						columns.add(shortname);
						columns.add(desc);

					}
				}, new ListStore<HbaseTable>(
						PropertyUtils.HbaseTableProperty.key()),
				gridConfig);
	}

}
