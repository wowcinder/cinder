package xdata.etl.cinder.web.client;


import xdata.etl.cinder.gwt.client.app.EtlApp;

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

				EtlApp app = new EtlApp();
				app.run();
//				Menu menu = new Menu();

				onReady();
				
			}

		});

	}

	private native void onReady() /*-{
		if (typeof $wnd.GxtReady != 'undefined') {
			$wnd.GxtReady();
		}
	}-*/;
}
