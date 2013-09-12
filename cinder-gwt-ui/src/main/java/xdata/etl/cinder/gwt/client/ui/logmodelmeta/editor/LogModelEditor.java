/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.editor;

import xdata.etl.cinder.gwt.client.common.combox.EnumComboBox;
import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class LogModelEditor extends CinderEditor<LogModel> {
	interface Driver extends SimpleBeanEditorDriver<LogModel, LogModelEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public LogModelEditor() {
		super(GWT.<Driver> create(Driver.class), "log_meta");
	}

	@Override
	protected void _update(LogModel t) {
		RpcServiceUtils.LogModelMetaRpcService.updateLogModel(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(LogModel t) {
		RpcServiceUtils.LogModelMetaRpcService.saveLogModel(t,
				getSaveOrUpdateAsyncCallback());

	}

	TextField name;
	TextArea desc;
	EnumComboBox<LogModelType> mtype;

	@Override
	protected void _initView() {
		name = new TextField();
		desc = new TextArea();
		mtype = new EnumComboBox<LogModelType>(LogModelType.values());

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
		layoutContainer.add(new FieldLabel(mtype, "mtype"), vd);

	}
}
