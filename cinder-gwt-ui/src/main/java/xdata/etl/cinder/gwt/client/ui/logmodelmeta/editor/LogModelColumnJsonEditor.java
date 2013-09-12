/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.LogModelColumnJson;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class LogModelColumnJsonEditor extends CinderEditor<LogModelColumnJson> {
	interface Dirver
			extends
			SimpleBeanEditorDriver<LogModelColumnJson, LogModelColumnJsonEditor> {

	}

	@Path("content.path")
	TextField path;

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public LogModelColumnJsonEditor() {
		super(GWT.<Dirver> create(Dirver.class), "日志模型字段");
	}

	@Override
	protected void _update(LogModelColumnJson t) {
		if (t.getVersion().getId() == null) {
			getLinkGwtCallBack().call(t);
		} else {
			// TODO
		}
	}

	@Override
	protected void _add(LogModelColumnJson t) {
		if (t.getVersion().getId() == null) {
			getLinkGwtCallBack().call(t);
		} else {
			// TODO
		}
	}

	@Override
	protected void _initView() {
		path = new TextField();
		layoutContainer.add(new FieldLabel(path, "path"), vd);
	}

}
