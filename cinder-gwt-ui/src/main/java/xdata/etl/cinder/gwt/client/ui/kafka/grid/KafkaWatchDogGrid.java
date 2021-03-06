/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.grid;

import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.KafkaWatchDogColumnConfig;
import xdata.etl.cinder.gwt.client.ui.kafka.window.KafkaWatchDogTopicSettingWindow;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
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

			ColumnConfig<KafkaWatchDog, String> topics = new ColumnConfig<KafkaWatchDog, String>(
					new ValueProvider<KafkaWatchDog, String>() {

						@Override
						public String getValue(KafkaWatchDog object) {
							return "topics";
						}

						@Override
						public void setValue(KafkaWatchDog object, String value) {

						}

						@Override
						public String getPath() {
							return null;
						}
					});
			TextButtonCell bt = new TextButtonCell();
			topics.setCell(bt);
			columns.add(ip);
			columns.add(name);
			columns.add(rmiPort);
			columns.add(topics);

			bt.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					KafkaWatchDog dog = getStore().get(
							event.getContext().getIndex());
					KafkaWatchDogTopicSettingWindow window = new KafkaWatchDogTopicSettingWindow(
							dog);
					window.show();
				}
			});
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
