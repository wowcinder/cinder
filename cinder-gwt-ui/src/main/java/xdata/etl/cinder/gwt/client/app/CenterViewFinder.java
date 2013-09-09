package xdata.etl.cinder.gwt.client.app;

import xdata.etl.cinder.gwt.client.ui.CenterView;

public abstract class CenterViewFinder {

	protected CenterViewFinder() {
	}

	public abstract CenterView findCenterView(String token);
}
