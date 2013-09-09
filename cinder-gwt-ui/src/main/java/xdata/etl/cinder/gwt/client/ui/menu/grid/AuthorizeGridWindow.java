/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.menu.grid;

import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;
import xdata.etl.cinder.gwt.client.common.window.FixedWindow;
import xdata.etl.cinder.shared.entity.authorize.Authorize;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class AuthorizeGridWindow extends FixedWindow {
	public AuthorizeGridWindow(GwtCallBack<Authorize> callback) {
		final AuthorizeGrid grid = new AuthorizeGrid(callback);
		setWidget(grid);
		setHeadingHtml("双击选择:");
		setHeight(450);
		setModal(true);

		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				grid.getCallback().call(
						grid.getStore().get(event.getRowIndex()));
				AuthorizeGridWindow.this.hide();
			}
		});
	}
}
