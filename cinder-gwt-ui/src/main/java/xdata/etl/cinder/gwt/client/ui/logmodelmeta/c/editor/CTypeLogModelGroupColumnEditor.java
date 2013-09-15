package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableVersionCombox;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class CTypeLogModelGroupColumnEditor extends
		CinderEditor<CTypeLogModelGroupColumn> {
	interface Driver
			extends
			SimpleBeanEditorDriver<CTypeLogModelGroupColumn, CTypeLogModelGroupColumnEditor> {

	}

	public CTypeLogModelGroupColumnEditor() {
		super(GWT.<Driver> create(Driver.class), "group_column");
	}

	@Override
	protected void update(CTypeLogModelGroupColumn t) {
		if (getCurrEditEvent().getTarget().getId() == null) {
			getLinkGwtCallBack().call(t);
		} else {
			super.update(t);
		}
	}

	@Override
	protected void _update(CTypeLogModelGroupColumn t) {
		// TODO
	}

	@Override
	protected void add(CTypeLogModelGroupColumn t) {
		if (getCurrEditEvent().getTarget().getGroupColumn().getId() == null) {
			getLinkGwtCallBack().call(t);
		} else {
			super.add(t);
		}
	}

	@Override
	protected void _add(CTypeLogModelGroupColumn t) {
		// TODO
	}

	HbaseTableVersionCombox hbaseTableVersion;
	TextField name;

	@Override
	protected void _initView() {
		name = new TextField();
		hbaseTableVersion = new HbaseTableVersionCombox();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(hbaseTableVersion, "hbase_version"),
				vd);
	}

}
