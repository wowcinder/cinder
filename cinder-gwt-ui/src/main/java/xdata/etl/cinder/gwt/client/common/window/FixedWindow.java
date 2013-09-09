/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.common.window;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;

import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class FixedWindow extends Window {

	private GwtCallBack<SelectEvent> cancelBack;

	private HandlerRegistration showHandlerHr;

	public FixedWindow() {
		showHandlerHr = this.addShowHandler(new ShowHandler() {
			@Override
			public void onShow(ShowEvent event) {
				showHandlerHr.removeHandler();
				FixedWindow.this.setMinHeight(FixedWindow.this.getElement()
						.getHeight(true));
			}
		});
	}

	public FixedWindow(GwtCallBack<SelectEvent> cancelBack) {
		this.cancelBack = cancelBack;
	}

	@Override
	protected void initTools() {
		super.initTools();
		if (isClosable()) {
			closeBtn.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					if (cancelBack != null) {
						cancelBack.call(event);
					}
				}
			});
		}
	}

	public GwtCallBack<SelectEvent> getCancelBack() {
		return cancelBack;
	}

	public void setCancelBack(GwtCallBack<SelectEvent> cancelBack) {
		this.cancelBack = cancelBack;
	}

}
