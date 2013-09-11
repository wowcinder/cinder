package xdata.etl.cinder.gwt.client.ui.user.grid;

import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.AuthorizeColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.authorize.AuthorizeGroup;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public class AuthorizeGrid extends CinderGrid<Authorize> {

	public AuthorizeGrid(GridConfig gridConfig) {
		super(
				new GridConfigProvider<Authorize>() {
					@Override
					public void load(EtlPagingLoadConfigBean loadConfig,
							AsyncCallback<PagingLoadResult<Authorize>> callback) {

						// TODO
					}

					@Override
					protected void initColumnConfig() {
						ColumnConfig<Authorize, AuthorizeGroup> group = AuthorizeColumnConfig
								.group();
						group.setCell(new SimpleSafeHtmlRenderer<AuthorizeGroup>() {
							@Override
							protected String _getLabel(AuthorizeGroup c) {
								return c.getName();
							}
						}.getCell());
						columns.add(group);
						ColumnConfig<Authorize, String> name = AuthorizeColumnConfig
								.name();
						columns.add(name);
					}
				}, new ListStore<Authorize>(
						PropertyUtils.AuthorizeProperty.key()),
				gridConfig);
	}
}
