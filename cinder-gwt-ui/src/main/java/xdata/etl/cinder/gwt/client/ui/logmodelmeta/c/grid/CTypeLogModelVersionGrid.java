package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.grid;

import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.CTypeLogModelVersionColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public class CTypeLogModelVersionGrid extends CinderGrid<CTypeLogModelVersion> {

	public CTypeLogModelVersionGrid(GridConfig gridConfig) {
		super(new GridConfigProvider<CTypeLogModelVersion>() {

			@Override
			public void load(
					EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<CTypeLogModelVersion>> callback) {
				RpcServiceUtils.CTypeLogModelMetaRpcService
						.pagingLogModelVersion(loadConfig, callback);
			}

			@Override
			protected void initColumnConfig() {
				ColumnConfig<CTypeLogModelVersion, CTypeLogModel> model = CTypeLogModelVersionColumnConfig
						.model();
				model.setCell(new SimpleSafeHtmlRenderer<CTypeLogModel>() {

					@Override
					protected String _getLabel(CTypeLogModel c) {
						return c.getName();
					}
				}.getCell());
				ColumnConfig<CTypeLogModelVersion, String> version = CTypeLogModelVersionColumnConfig
						.version();
				ColumnConfig<CTypeLogModelVersion, CTypeLogModelGroupColumn> hbaseTable = CTypeLogModelVersionColumnConfig
						.rootNode();
				hbaseTable
						.setCell(new SimpleSafeHtmlRenderer<CTypeLogModelGroupColumn>() {
							@Override
							protected String _getLabel(
									CTypeLogModelGroupColumn c) {
								return c.getHbaseTableVersion().getTable()
										.getName();
							}
						}.getCell());
				hbaseTable.setHeader("hbaseTable");
				ColumnConfig<CTypeLogModelVersion, CTypeLogModelGroupColumn> hbaseTableVersion = CTypeLogModelVersionColumnConfig
						.rootNode();
				hbaseTableVersion
						.setCell(new SimpleSafeHtmlRenderer<CTypeLogModelGroupColumn>() {
							@Override
							protected String _getLabel(
									CTypeLogModelGroupColumn c) {
								return c.getHbaseTableVersion().getVersion();
							}
						}.getCell());
				hbaseTableVersion.setHeader("hbaseTableVersion");
				ColumnConfig<CTypeLogModelVersion, String> desc = CTypeLogModelVersionColumnConfig
						.desc();

				columns.add(model);
				columns.add(version);
				columns.add(hbaseTable);
				columns.add(hbaseTableVersion);
				columns.add(desc);
			}
		}, new ListStore<CTypeLogModelVersion>(
				PropertyUtils.CTypeLogModelVersionProperty.key()), gridConfig);
	}

}
