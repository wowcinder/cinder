package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.combox;

import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableVersionCombox;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public abstract class GroupColumnHbaseTableVersionCombox extends
		HbaseTableVersionCombox {
	private HandlerRegistration valueChangeHr;

	public GroupColumnHbaseTableVersionCombox() {
		super();
		addSelectionHandler(new SelectionHandler<HbaseTableVersion>() {

			@Override
			public void onSelection(SelectionEvent<HbaseTableVersion> event) {
				if (isAddItem(event.getSelectedItem())) {
					valueChangeHr = addValueChangeHandler(new ValueChangeHandler<HbaseTableVersion>() {

						@Override
						public void onValueChange(
								ValueChangeEvent<HbaseTableVersion> event) {
							if (isAddItem(event.getValue())) {
								return;
							}
							valueChangeHr.removeHandler();
							valueChangeHr = null;
							checkHbaseTableColumn(event.getValue());

						}
					});
				} else {
					checkHbaseTableColumn(event.getSelectedItem());
				}
			}
		});
	}

	protected abstract void checkHbaseTableColumn(HbaseTableVersion currVersion);
}