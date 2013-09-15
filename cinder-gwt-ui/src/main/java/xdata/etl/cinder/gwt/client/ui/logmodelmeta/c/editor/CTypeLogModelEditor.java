/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class CTypeLogModelEditor extends CinderEditor<CTypeLogModel> {
	interface Driver extends
			SimpleBeanEditorDriver<CTypeLogModel, CTypeLogModelEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public CTypeLogModelEditor() {
		super(GWT.<Driver> create(Driver.class), "CTypeLogModel");
	}

	@Override
	protected void _update(CTypeLogModel t) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.updateLogModel(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(CTypeLogModel t) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.saveLogModel(t,
				getSaveOrUpdateAsyncCallback());

	}

	TextField name;
	TextArea desc;

	@Override
	protected void _initView() {
		name = new TextField();
		desc = new TextArea();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);

	}
}
