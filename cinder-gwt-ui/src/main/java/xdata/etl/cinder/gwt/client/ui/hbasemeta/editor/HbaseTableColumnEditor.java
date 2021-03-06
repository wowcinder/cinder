/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.hbasemeta.editor;

import xdata.etl.cinder.gwt.client.common.combox.EnumComboBox;
import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn.HbaseTableColumnType;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class HbaseTableColumnEditor extends CinderEditor<HbaseTableColumn> {
	public interface Driver extends
			SimpleBeanEditorDriver<HbaseTableColumn, HbaseTableColumnEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public HbaseTableColumnEditor() {
		super(GWT.<Driver> create(Driver.class), "hbase_column");
	}

	@Override
	protected void _update(HbaseTableColumn t) {
		if (getCurrEditEvent().getTarget().getVersion().getId() == null) {
			getLinkGwtCallBack().call(t);
		} else {
			RpcServiceUtils.HbaseMetaRpcService.updateHbaseTableColumn(t,
					getSaveOrUpdateAsyncCallback());
		}
	}

	@Override
	protected void _add(HbaseTableColumn t) {
		if (getCurrEditEvent().getTarget().getVersion().getId() == null) {
			getLinkGwtCallBack().call(t);
		} else {
			RpcServiceUtils.HbaseMetaRpcService.saveHbaseTableColumn(t,
					getSaveOrUpdateAsyncCallback());
		}
	}

	TextField name;
	TextField shortname;
	EnumComboBox<HbaseTableColumnType> type;
	TextField desc;

	@Override
	protected void _initView() {
		name = new TextField();
		shortname = new TextField();
		type = new EnumComboBox<HbaseTableColumnType>(
				HbaseTableColumnType.values());
		desc = new TextField();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(shortname, "shortname"), vd);
		layoutContainer.add(new FieldLabel(type, "type"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
	}

}
