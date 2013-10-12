/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.window;

import xdata.etl.cinder.gwt.client.common.window.FixedWindow;
import xdata.etl.cinder.gwt.client.ui.SimpleCenterView;
import xdata.etl.cinder.gwt.client.ui.kafka.grid.KafkaWatchDogTopicSettingMonitorGird;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
public class KafkaWatchDogTopicSettingMonitorWindow extends FixedWindow {

	public static class KafkaWatchDogTopicSettingView extends SimpleCenterView {
		private final KafkaWatchDogTopicSettingMonitorGird grid;

		public KafkaWatchDogTopicSettingView(
				KafkaWatchDogTopicSettingMonitorGird grid) {
			add(grid, mainVd);
			this.grid = grid;
		}

		public KafkaWatchDogTopicSettingMonitorGird getGrid() {
			return grid;
		}

	}

	private final KafkaWatchDogTopicSettingMonitorGird grid;
	private final KafkaWatchDogTopicSettingView view;

	public KafkaWatchDogTopicSettingMonitorWindow(KafkaWatchDog watchDog) {
		setHeadingText("topic_setting");
		grid = new KafkaWatchDogTopicSettingMonitorGird(watchDog);
		view = new KafkaWatchDogTopicSettingView(grid);

		setWidget(view);
	}

	@Override
	protected void onShow() {
		super.onShow();
		/*
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				// TODO
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
