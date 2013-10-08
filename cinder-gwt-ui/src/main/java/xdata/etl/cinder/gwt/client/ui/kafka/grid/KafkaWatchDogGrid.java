/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.grid;

import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.KafkaWatchDogColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog.KafkaProcessServerStatus;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public class KafkaWatchDogGrid extends CinderGrid<KafkaWatchDog> {

	public static class KafkaWatchDogGridConfigProvider extends
			GridConfigProvider<KafkaWatchDog> {

		/**
		 * @param store
		 */
		public KafkaWatchDogGridConfigProvider() {
			super(new ListStore<KafkaWatchDog>(
					PropertyUtils.KafkaWatchDogProperty.key()));
		}

		@Override
		public void load(EtlPagingLoadConfigBean loadConfig,
				AsyncCallback<PagingLoadResult<KafkaWatchDog>> callback) {
			RpcServiceUtils.KafkaRpcService.pagingKafkaWatchDog(loadConfig,
					callback);
		}

		@Override
		protected void initColumnConfig() {
			ColumnConfig<KafkaWatchDog, String> ip = KafkaWatchDogColumnConfig
					.ip();
			ColumnConfig<KafkaWatchDog, String> name = KafkaWatchDogColumnConfig
					.name();
			ColumnConfig<KafkaWatchDog, Integer> rmiPort = KafkaWatchDogColumnConfig
					.rmiPort();
			ColumnConfig<KafkaWatchDog, KafkaWatchDog.KafkaProcessServerStatus> status = KafkaWatchDogColumnConfig
					.status();
			status.setCell(new SimpleSafeHtmlRenderer<KafkaProcessServerStatus>() {
				@Override
				protected String _getLabel(KafkaProcessServerStatus c) {
					return c.name();
				}
			}.getCell());
			columns.add(ip);
			columns.add(name);
			columns.add(rmiPort);
			columns.add(status);
		}

	}

	/**
	 * @param configProvider
	 * @param gridConfig
	 */
	public KafkaWatchDogGrid(GridConfig gridConfig) {
		super(new KafkaWatchDogGridConfigProvider(), gridConfig);
	}

}
