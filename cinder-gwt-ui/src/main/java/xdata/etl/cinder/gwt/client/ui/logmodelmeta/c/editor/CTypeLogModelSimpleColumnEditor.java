package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.common.event.EditEvent;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableColumnCombox;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableColumnComboxAddEvent;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class CTypeLogModelSimpleColumnEditor extends
		CinderEditor<CTypeLogModelSimpleColumn> {
	interface Driver
			extends
			SimpleBeanEditorDriver<CTypeLogModelSimpleColumn, CTypeLogModelSimpleColumnEditor> {

	}

	public CTypeLogModelSimpleColumnEditor() {
		super(GWT.<Driver> create(Driver.class), "simple_column");
	}

	@Override
	protected void update(CTypeLogModelSimpleColumn t) {
		if (getCurrEditEvent().getTarget().getId() == null) {
			getLinkGwtCallBack().call(t);
		} else {
			super.update(t);
		}
	}

	@Override
	protected void _update(CTypeLogModelSimpleColumn t) {
		// TODO
	}

	@Override
	protected void add(CTypeLogModelSimpleColumn t) {
		if (getCurrEditEvent().getTarget().getGroupColumn().getId() == null) {
			getLinkGwtCallBack().call(t);
		} else {
			super.add(t);
		}
	}

	@Override
	protected void _add(CTypeLogModelSimpleColumn t) {
		// TODO
	}

	HbaseTableColumnCombox hbaseTableColumn;
	TextField name;

	@Override
	protected void _initView() {
		name = new TextField();
		hbaseTableColumn = new HbaseTableColumnCombox();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(hbaseTableColumn, "hbase_column"),
				vd);
	}

	@Override
	public void onEdit(EditEvent<CTypeLogModelSimpleColumn> event) {
		hbaseTableColumn.fireRefreshCombox(new HbaseTableColumnComboxAddEvent(
				event.getTarget().getGroupColumn().getHbaseTableVersion()));
		super.onEdit(event);
	}

}
