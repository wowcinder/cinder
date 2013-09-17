package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.editor;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;
import xdata.etl.cinder.gwt.client.common.LinkGwtCallBack;
import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.common.event.EditEvent;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableVersionCombox;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.tree.CTypeLogModelColumnTree;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.tree.HbaseTableVersionChangeEvent;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
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

	private final CTypeLogModelColumnTree tree;

	public CTypeLogModelGroupColumnEditor(CTypeLogModelColumnTree tree) {
		super(GWT.<Driver> create(Driver.class), "group_column");
		this.tree = tree;
	}

	private HbaseTableVersion oldVersion;

	@Override
	public void onEdit(EditEvent<CTypeLogModelGroupColumn> event) {
		oldVersion = event.getTarget().getHbaseTableVersion();
		super.onEdit(event);
	}

	@Override
	protected void _update(CTypeLogModelGroupColumn t) {
		GwtCallBack<CTypeLogModelGroupColumn> callback = new LinkGwtCallBack<CTypeLogModelGroupColumn, CTypeLogModelGroupColumn>(
				getLinkGwtCallBack()) {
			@Override
			protected void _call(CTypeLogModelGroupColumn t) {
				_swapperCall(t);
				if (oldVersion != null
						&& (t.getHbaseTableVersion() == null || !oldVersion
								.getId().equals(
										t.getHbaseTableVersion().getId()))) {
					tree.fireEvent(new HbaseTableVersionChangeEvent(t));
				}
			}
		};
		callback.call(t);
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
