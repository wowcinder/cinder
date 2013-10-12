/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.grid;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.cell.SimpleSafeHtmlRenderer;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.grid.GridConfigProvider;
import xdata.etl.cinder.gwt.client.gridcolumn.KafkaWatchDogTopicSettingColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public class KafkaWatchDogTopicSettingMonitorGird extends
		CinderGrid<KafkaWatchDogTopicSetting> {

	public static class KafkaWatchDogTopicSettingGridConfigProvider extends
			GridConfigProvider<KafkaWatchDogTopicSetting> {

		/**
		 * @param store
		 */
		public KafkaWatchDogTopicSettingGridConfigProvider() {
			super(new ListStore<KafkaWatchDogTopicSetting>(
					PropertyUtils.KafkaWatchDogTopicSettingProperty.key()));
		}

		@Override
		public void load(
				EtlPagingLoadConfigBean loadConfig,
				AsyncCallback<PagingLoadResult<KafkaWatchDogTopicSetting>> callback) {

		}

		@Override
		protected void initColumnConfig() {
			ColumnConfig<KafkaWatchDogTopicSetting, KafkaTopic> topic = KafkaWatchDogTopicSettingColumnConfig
					.topic();
			topic.setCell(new SimpleSafeHtmlRenderer<KafkaTopic>() {
				@Override
				protected String _getLabel(KafkaTopic c) {
					return c.getName();
				}
			}.getCell());
			ColumnConfig<KafkaWatchDogTopicSetting, KafkaWatchDogTopicSettingStatus> status = KafkaWatchDogTopicSettingColumnConfig
					.status();
			status.setCell(new SimpleSafeHtmlRenderer<KafkaWatchDogTopicSettingStatus>() {
				@Override
				protected String _getLabel(KafkaWatchDogTopicSettingStatus c) {
					return c.name();
				}
			}.getCell());
			columns.add(topic);
			columns.add(status);
		}

	}

	private final KafkaWatchDog watchDog;

	public KafkaWatchDogTopicSettingMonitorGird(KafkaWatchDog watchDog) {
		this(new GridConfig(false, false), watchDog);
	}

	public KafkaWatchDogTopicSettingMonitorGird(GridConfig gridConfig,
			KafkaWatchDog watchDog) {
		super(new KafkaWatchDogTopicSettingGridConfigProvider(), gridConfig);
		this.watchDog = watchDog;
		setHeight(350);
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RpcServiceUtils.KafkaRpcService
						.getKafkaWatchDogTopicSettingStatuss(
								getWatchDog().getId(),
								new RpcAsyncCallback<List<KafkaWatchDogTopicSetting>>() {

									@Override
									public void _onSuccess(
											List<KafkaWatchDogTopicSetting> t) {
										getStore().clear();
										getStore().addAll(t);
									}
								});
			}
		});
	}

	public KafkaWatchDog getWatchDog() {
		return watchDog;
	}

}
