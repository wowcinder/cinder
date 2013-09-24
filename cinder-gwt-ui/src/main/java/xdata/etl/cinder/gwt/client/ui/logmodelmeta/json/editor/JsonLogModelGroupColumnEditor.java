package xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.editor;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;
import xdata.etl.cinder.gwt.client.common.LinkGwtCallBack;
import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.common.event.EditEvent;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableVersionCombox;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.HbaseTableVersionChangeEvent;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.tree.JsonLogModelColumnTree;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import xdata.etl.cinder.shared.HbaseVersionChangeUtil;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class JsonLogModelGroupColumnEditor extends
		CinderEditor<JsonLogModelGroupColumn> {
	interface Driver
			extends
			SimpleBeanEditorDriver<JsonLogModelGroupColumn, JsonLogModelGroupColumnEditor> {

	}

	private final JsonLogModelColumnTree tree;

	public JsonLogModelGroupColumnEditor(JsonLogModelColumnTree tree) {
		super(GWT.<Driver> create(Driver.class), "group_column");
		this.tree = tree;
	}

	private HbaseTableVersion oldVersion;

	@Override
	public void onEdit(EditEvent<JsonLogModelGroupColumn> event) {
		oldVersion = event.getTarget().getHbaseTableVersion();
		super.onEdit(event);
	}

	@Override
	protected void _update(JsonLogModelGroupColumn t) {
		GwtCallBack<JsonLogModelGroupColumn> callback = new LinkGwtCallBack<JsonLogModelGroupColumn, JsonLogModelGroupColumn>(
				getLinkGwtCallBack()) {
			@Override
			protected void _call(JsonLogModelGroupColumn t) {
				_swapperCall(t);
				if (HbaseVersionChangeUtil.isChange(oldVersion,
						t.getHbaseTableVersion())) {
					tree.fireEvent(new HbaseTableVersionChangeEvent<JsonLogModelGroupColumn>(
							t));
				}
			}
		};
		RpcServiceUtils.JsonLogModelMetaRpcService.updateLogModelGroupColumn(t,
				RpcAsyncCallback.dealWith(callback));
	}

	@Override
	protected void _add(JsonLogModelGroupColumn t) {
		RpcServiceUtils.JsonLogModelMetaRpcService.saveLogModelGroupColumn(t,
				getSaveOrUpdateAsyncCallback());
	}

	HbaseTableVersionCombox hbaseTableVersion;
	TextField path;

	@Override
	protected void _initView() {
		path = new TextField();
		hbaseTableVersion = new HbaseTableVersionCombox();

		layoutContainer.add(new FieldLabel(path, "path"), vd);
		layoutContainer.add(new FieldLabel(hbaseTableVersion, "hbase_version"),
				vd);
	}
}
