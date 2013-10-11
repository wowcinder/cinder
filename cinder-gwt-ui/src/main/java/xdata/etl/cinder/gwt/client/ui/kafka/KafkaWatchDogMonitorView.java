/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka;

import xdata.etl.cinder.gwt.client.ui.SimpleCenterView;
import xdata.etl.cinder.gwt.client.ui.kafka.grid.KafkaWatchDogMonitorGrid;
import xdata.etl.cinder.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年10月11日
 */
@MenuToken(name = "WatchDog_MONITOR", token = "watch_dog_monitor", group = "KAFKA")
public class KafkaWatchDogMonitorView extends SimpleCenterView {
	private final KafkaWatchDogMonitorGrid grid;

	public KafkaWatchDogMonitorView() {
		this.grid = new KafkaWatchDogMonitorGrid();
		add(this.grid, mainVd);
	}
}
