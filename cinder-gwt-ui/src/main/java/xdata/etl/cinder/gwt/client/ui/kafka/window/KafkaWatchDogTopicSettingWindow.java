/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.window;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.window.FixedWindow;
import xdata.etl.cinder.gwt.client.ui.AbstractCenterView;
import xdata.etl.cinder.gwt.client.ui.kafka.editor.KafkaWatchDogTopicSettingEditor;
import xdata.etl.cinder.gwt.client.ui.kafka.grid.KafkaWatchDogTopicSettingGrid;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public class KafkaWatchDogTopicSettingWindow extends FixedWindow {

	public static class KafkaWatchDogTopicSettingView extends
			AbstractCenterView<KafkaWatchDogTopicSetting> {

		/**
		 * @param grid
		 * @param editor
		 */
		public KafkaWatchDogTopicSettingView(
				CinderGrid<KafkaWatchDogTopicSetting> grid) {
			super(grid, new KafkaWatchDogTopicSettingEditor());
		}

		@Override
		protected KafkaWatchDogTopicSetting newViewInstance() {
			KafkaWatchDogTopicSetting setting = new KafkaWatchDogTopicSetting();
			setting.setServer(getGrid().getWatchDog());
			return setting;
		}

		@Override
		protected void delete(List<KafkaWatchDogTopicSetting> list) {
			RpcServiceUtils.KafkaRpcService
					.deleteKafkaWatchDogTopicSettings(
							getIds(list,
									new EntityToKey<KafkaWatchDogTopicSetting, Integer>() {

										@Override
										public Integer getId(
												KafkaWatchDogTopicSetting m) {
											return m.getId();
										}
									}), getAsyncCallback(list));
		}

		@Override
		public KafkaWatchDogTopicSettingGrid getGrid() {
			return (KafkaWatchDogTopicSettingGrid) super.getGrid();
		}

	}

	private final KafkaWatchDogTopicSettingGrid grid;
	private final KafkaWatchDogTopicSettingView view;

	public KafkaWatchDogTopicSettingWindow(KafkaWatchDog watchDog) {
		setHeadingText("topic_setting");
		grid = new KafkaWatchDogTopicSettingGrid(watchDog);
		view = new KafkaWatchDogTopicSettingView(grid);

		setWidget(view);
	}

	@Override
	protected void onShow() {
		super.onShow();
		/*Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RpcServiceUtils.KafkaRpcService
						.getKafkaWatchDogTopicSettings(
								grid.getWatchDog().getId(),
								new RpcAsyncCallback<List<KafkaWatchDogTopicSetting>>() {

									@Override
									public void _onSuccess(
											List<KafkaWatchDogTopicSetting> t) {
										grid.getStore().clear();
										grid.getStore().addAll(t);
									}
								});
			}
		});*/
	}
}
