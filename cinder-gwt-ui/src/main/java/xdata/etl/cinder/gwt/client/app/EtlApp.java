package xdata.etl.cinder.gwt.client.app;

import com.google.gwt.user.client.ui.RootPanel;

public class EtlApp {
	public void run() {
		RootPanel.get().add(new EtlView());
	}
}
