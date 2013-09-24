package xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.common.event.EditEvent;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableColumnCombox;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableColumnComboxAddEvent;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class JsonLogModelSimpleColumnEditor extends
		CinderEditor<JsonLogModelSimpleColumn> {
	interface Driver
			extends
			SimpleBeanEditorDriver<JsonLogModelSimpleColumn, JsonLogModelSimpleColumnEditor> {

	}

	public JsonLogModelSimpleColumnEditor() {
		super(GWT.<Driver> create(Driver.class), "simple_column");
	}

	@Override
	protected void _update(JsonLogModelSimpleColumn t) {
		RpcServiceUtils.JsonLogModelMetaRpcService.updateLogModelSimpleColumn(
				t, getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(JsonLogModelSimpleColumn t) {
		RpcServiceUtils.JsonLogModelMetaRpcService.saveLogModelSimpleColumn(t,
				getSaveOrUpdateAsyncCallback());
	}

	HbaseTableColumnCombox hbaseTableColumn;
	TextField path;

	@Override
	protected void _initView() {
		path = new TextField();
		hbaseTableColumn = new HbaseTableColumnCombox();

		layoutContainer.add(new FieldLabel(path, "path"), vd);
		layoutContainer.add(new FieldLabel(hbaseTableColumn, "hbase_column"),
				vd);
	}

	@Override
	public void onEdit(EditEvent<JsonLogModelSimpleColumn> event) {
		hbaseTableColumn.fireRefreshCombox(new HbaseTableColumnComboxAddEvent(
				event.getTarget().getGroupColumn().getHbaseTableVersion()));
		super.onEdit(event);
	}

}
