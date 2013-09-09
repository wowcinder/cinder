/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui;

import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
public class SimpleCenterView extends HorizontalLayoutContainer implements
		CenterView {

	private CenterViewConfig centerViewConfig;

	@Override
	public CenterViewConfig getCenterViewConfig() {
		return centerViewConfig;
	}

	@Override
	public void setCenterViewConfig(CenterViewConfig centerViewConfig) {
		this.centerViewConfig = centerViewConfig;
	}

}
