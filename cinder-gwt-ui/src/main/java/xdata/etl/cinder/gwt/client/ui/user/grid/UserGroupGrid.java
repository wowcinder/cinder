package xdata.etl.cinder.gwt.client.ui.user.grid;

import java.util.Date;

import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.UserGroupColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.shared.entity.user.UserGroup;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

public class UserGroupGrid extends CinderGrid<UserGroup> {
	public static final DateTimeFormat DF = DateTimeFormat
			.getFormat("yyyy-MM-dd HH:mm:ss");

	public static final GridConfigProvider<UserGroup> CONFIG_PROVIDER = new GridConfigProvider<UserGroup>() {

		@Override
		public void load(EtlPagingLoadConfigBean loadConfig,
				AsyncCallback<PagingLoadResult<UserGroup>> callback) {
			RpcServiceUtils.UserRpcService
					.pagingUserGroup(loadConfig, callback);
		}

		@Override
		protected void initColumnConfig() {

			ColumnConfig<UserGroup, String> name = UserGroupColumnConfig.name();
			ColumnConfig<UserGroup, Date> createTime = UserGroupColumnConfig
					.createTime();
			createTime.setCell(new SimpleSafeHtmlRenderer<Date>() {
				@Override
				protected String _getLabel(Date c) {
					return DF.format(c);
				}
			}.getCell());
			columns.add(name);
			columns.add(createTime);

		}
	};

	public UserGroupGrid() {
		super(CONFIG_PROVIDER, new ListStore<UserGroup>(
				PropertyUtils.UserGroupProperty.key()));
	}

}
