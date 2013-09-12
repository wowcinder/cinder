/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class LogModelVersionEditor extends CinderEditor<LogModelVersion> {
	interface Driver extends
			SimpleBeanEditorDriver<LogModelVersion, LogModelVersionEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public LogModelVersionEditor() {
		super(GWT.<Driver> create(Driver.class), "日志模型版本");
	}

	@Override
	protected void _update(LogModelVersion t) {

	}

	@Override
	protected void _add(LogModelVersion t) {

	}

	@Override
	protected void _initView() {

	}

}
