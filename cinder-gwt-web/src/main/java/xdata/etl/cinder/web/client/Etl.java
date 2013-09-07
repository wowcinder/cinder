package xdata.etl.cinder.web.client;

import xdata.etl.cinder.gwt.client.SimplePanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Etl implements EntryPoint {
	public void onModuleLoad() {
		SimplePanel panel = new SimplePanel();
		panel.setHeight(300);
		panel.setWidth(200);

		RootPanel.get().add(panel);
	}
}
