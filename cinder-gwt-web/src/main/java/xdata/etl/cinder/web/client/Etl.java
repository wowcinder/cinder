package xdata.etl.cinder.web.client;

import xdata.etl.cinder.gwt.client.app.EtlApp;
import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.ui.LoginWindow;
import xdata.etl.cinder.gwt.client.ui.LoginWindow.LoginSucessCallBack;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.state.client.CookieProvider;
import com.sencha.gxt.state.client.StateManager;

public class Etl implements EntryPoint {
	public void onModuleLoad() {

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				StateManager.get().setProvider(
						new CookieProvider("/", null, null, GXT.isSecure()));

				initView();
				RpcServiceUtils.OpenAuthorizeRpcService
						.isLogin(new RpcAsyncCallback<Boolean>() {
							@Override
							public void _onSuccess(Boolean t) {
								if (t) {
									initView();
								} else {
									LoginWindow window = new LoginWindow();
									window.setCallback(new LoginSucessCallBack() {
										@Override
										public void logined() {
											initView();
										}
									});
									window.show();
								}
							}
						});

				onReady();

			}

		});

	}

	public void initView() {
		EtlApp app = new EtlApp();
		app.run();
	}

	private native void onReady() /*-{
		if (typeof $wnd.GxtReady != 'undefined') {
			$wnd.GxtReady();
		}
	}-*/;
}
