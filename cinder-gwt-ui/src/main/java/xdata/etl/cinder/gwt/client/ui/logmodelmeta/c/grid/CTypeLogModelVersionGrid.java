package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.grid;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;
import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.event.EditEvent;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.CTypeLogModelVersionColumnConfig;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.editor.CTypeLogModelMappingEditor;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public class CTypeLogModelVersionGrid extends CinderGrid<CTypeLogModelVersion> {
	public static class CTypeLogModelVersionGridProvider extends
			GridConfigProvider<CTypeLogModelVersion> {
		private final CTypeLogModelMappingEditor mappingEditor;

		public CTypeLogModelVersionGridProvider() {
			super(new ListStore<CTypeLogModelVersion>(
					PropertyUtils.CTypeLogModelVersionProperty.key()));
			mappingEditor = new CTypeLogModelMappingEditor();
		}

		@Override
		public void load(EtlPagingLoadConfigBean loadConfig,
				AsyncCallback<PagingLoadResult<CTypeLogModelVersion>> callback) {
			RpcServiceUtils.CTypeLogModelMetaRpcService.pagingLogModelVersion(
					loadConfig, callback);
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
						protected String _getLabel(CTypeLogModelGroupColumn c) {
							if (c.getHbaseTableVersion() != null) {
								return c.getHbaseTableVersion().getTable()
										.getName();
							}
							return null;
						}
					}.getCell());
			hbaseTable.setHeader("hbaseTable");
			ColumnConfig<CTypeLogModelVersion, CTypeLogModelGroupColumn> hbaseTableVersion = CTypeLogModelVersionColumnConfig
					.rootNode();
			hbaseTableVersion
					.setCell(new SimpleSafeHtmlRenderer<CTypeLogModelGroupColumn>() {
						@Override
						protected String _getLabel(CTypeLogModelGroupColumn c) {
							if (c.getHbaseTableVersion() != null) {
								return c.getHbaseTableVersion().getVersion();
							}
							return null;
						}
					}.getCell());
			hbaseTableVersion.setHeader("hbaseTableVersion");
			ColumnConfig<CTypeLogModelVersion, String> desc = CTypeLogModelVersionColumnConfig
					.desc();

			ColumnConfig<CTypeLogModelVersion, String> bt = new ColumnConfig<CTypeLogModelVersion, String>(
					new ValueProvider<CTypeLogModelVersion, String>() {

						@Override
						public String getValue(CTypeLogModelVersion object) {
							return "修改mapping";
						}

						@Override
						public void setValue(CTypeLogModelVersion object,
								String v) {

						}

						@Override
						public String getPath() {
							return null;
						}
					});
			TextButtonCell tbCell = new TextButtonCell();
			bt.setCell(tbCell);
			tbCell.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					mappingEditor
							.fireEditEvent(new EditEvent<CTypeLogModelVersion>(
									getStore().get(
											event.getContext().getIndex()),
									new GwtCallBack<CTypeLogModelVersion>() {
										@Override
										protected void _call(
												CTypeLogModelVersion t) {

										}
									}, true));
				}
			});
			columns.add(model);
			columns.add(version);
			columns.add(hbaseTable);
			columns.add(hbaseTableVersion);
			columns.add(desc);
			columns.add(bt);

		}
	}

	public CTypeLogModelVersionGrid(GridConfig gridConfig) {
		super(new CTypeLogModelVersionGridProvider(),

		gridConfig);
	}

}
