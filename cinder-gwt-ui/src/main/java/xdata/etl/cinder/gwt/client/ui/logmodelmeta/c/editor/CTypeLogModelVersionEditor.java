/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableVersionCombox;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.combox.CTypeLogModelCombox;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class CTypeLogModelVersionEditor extends
		CinderEditor<CTypeLogModelVersion>  {
	interface Driver
			extends
			SimpleBeanEditorDriver<CTypeLogModelVersion, CTypeLogModelVersionEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public CTypeLogModelVersionEditor() {
		super(GWT.<Driver> create(Driver.class), "日志模型版本");
	}

	@Override
	protected void _update(CTypeLogModelVersion t) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.updateLogModelVersion(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(CTypeLogModelVersion t) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.saveLogModelVersion(t,
				getSaveOrUpdateAsyncCallback());
	}

	CTypeLogModelCombox model;
	TextField version;
	@Path("rootNode.hbaseTableVersion")
	HbaseTableVersionCombox hbaseTableVersion;
	TextArea desc;
	@Ignore
	CTypeLogModelGroupColumn rootNode;

	@Override
	protected void _initView() {
		model = new CTypeLogModelCombox();
		version = new TextField();
		hbaseTableVersion = new HbaseTableVersionCombox();
		desc = new TextArea();
		layoutContainer.add(new FieldLabel(model, "model"), vd);
		layoutContainer.add(new FieldLabel(version, "version"), vd);
		layoutContainer.add(new FieldLabel(hbaseTableVersion,
				"hbaseTableVersion"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
	}

	protected String getColumnKey(CTypeLogModelColumn item) {
		if (item.getGroupColumn() == null) {
			return item.getName();
		} else {
			return getColumnKey(item.getGroupColumn()) + "#" + item.getName();
		}
	}
}
