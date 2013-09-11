/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.hbasequery.grid;

import xdata.etl.cinder.gwt.client.ui.hbasequery.grid.GridHeaderReadyEvent.GridHeaderReadyHanlder;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class GridHeaderReadyEvent extends GwtEvent<GridHeaderReadyHanlder> {
	public static final Type<GridHeaderReadyHanlder> TYPE = new Type<GridHeaderReadyHanlder>();

	public interface GridHeaderReadyHanlder extends EventHandler {
		public void onHeaderReady();
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GridHeaderReadyHanlder> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GridHeaderReadyHanlder handler) {
		handler.onHeaderReady();
	}
}
