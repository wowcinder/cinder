/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.common.grid;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class CinderGrid<M> extends Grid<M> {
	private final GridConfig gridConfig;

	public CinderGrid(GridConfigProvider<M> configProvider, ListStore<M> store) {
		this(configProvider, store, new GridConfig());
	}

	public CinderGrid(GridConfigProvider<M> configProvider, ListStore<M> store,
			GridConfig gridConfig) {
		super(store, new ColumnModel<M>(configProvider.getColumns(gridConfig
				.isMultiSelect())));
		configProvider.initSelectModel(this, gridConfig.isMultiSelect());
		configProvider.initPaging(this, gridConfig);
		this.gridConfig = gridConfig;
		setLoadMask(true);
	}

	public GridConfig getGridConfig() {
		return gridConfig;
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		if (getGridConfig().isPaging()) {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					getLoader().load();
				}
			});
		}

	}
}
