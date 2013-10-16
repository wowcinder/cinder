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
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.messages.client.DefaultMessages;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public class KafkaWatchDogTopicSettingGrid extends
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
			ColumnConfig<KafkaWatchDogTopicSetting, Integer> threadNum = KafkaWatchDogTopicSettingColumnConfig
					.threadNum();
			ColumnConfig<KafkaWatchDogTopicSetting, Boolean> isEnabled = KafkaWatchDogTopicSettingColumnConfig
					.isEnabled();
			columns.add(topic);
			columns.add(threadNum);
			columns.add(isEnabled);
		}

	}

	private final KafkaWatchDog watchDog;

	public KafkaWatchDogTopicSettingGrid(KafkaWatchDog watchDog) {
		this(new GridConfig(true, false), watchDog);
	}

	public KafkaWatchDogTopicSettingGrid(GridConfig gridConfig,
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
				mask(DefaultMessages.getMessages().loadMask_msg());
				RpcServiceUtils.KafkaRpcService
						.getKafkaWatchDogTopicSettings(
								getWatchDog().getId(),
								new RpcAsyncCallback<List<KafkaWatchDogTopicSetting>>() {

									@Override
									public void _onSuccess(
											List<KafkaWatchDogTopicSetting> t) {
										getStore().clear();
										getStore().addAll(t);
									}

									@Override
									public void post() {
										super.post();
										unmask();
									}
								});
			}
		});
	}

	public KafkaWatchDog getWatchDog() {
		return watchDog;
	}

}
