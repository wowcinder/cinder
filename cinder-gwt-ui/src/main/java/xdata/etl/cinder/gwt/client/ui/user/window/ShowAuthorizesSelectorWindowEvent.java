package xdata.etl.cinder.gwt.client.ui.user.window;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;
import xdata.etl.cinder.gwt.client.ui.user.window.ShowAuthorizesSelectorWindowEvent.ShowAuthorizesSelectorWindowEventHandler;
import xdata.etl.cinder.shared.entity.authorize.Authorize;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ShowAuthorizesSelectorWindowEvent extends
		GwtEvent<ShowAuthorizesSelectorWindowEventHandler> {
	public static final Type<ShowAuthorizesSelectorWindowEventHandler> TYPE = new Type<ShowAuthorizesSelectorWindowEventHandler>();

	public interface ShowAuthorizesSelectorWindowEventHandler extends
			EventHandler {
		void dealEvent(ShowAuthorizesSelectorWindowEvent event);
	}

	private GwtCallBack<List<Authorize>> callback;

	public ShowAuthorizesSelectorWindowEvent(
			GwtCallBack<List<Authorize>> callback) {
		this.callback = callback;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowAuthorizesSelectorWindowEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowAuthorizesSelectorWindowEventHandler handler) {
		handler.dealEvent(this);
	}

	public GwtCallBack<List<Authorize>> getCallback() {
		return callback;
	}
}
