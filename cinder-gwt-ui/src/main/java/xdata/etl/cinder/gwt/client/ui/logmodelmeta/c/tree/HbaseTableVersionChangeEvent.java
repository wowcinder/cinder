package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.tree;

import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.tree.HbaseTableVersionChangeEvent.HbaseTableVersionChangeHanlder;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class HbaseTableVersionChangeEvent extends
		GwtEvent<HbaseTableVersionChangeHanlder> {
	public static final Type<HbaseTableVersionChangeHanlder> TYPE = new Type<HbaseTableVersionChangeHanlder>();

	public interface HbaseTableVersionChangeHanlder extends EventHandler {
		public void onCheckVersionChange(HbaseTableVersionChangeEvent event);

	}

	private final CTypeLogModelGroupColumn column;

	public HbaseTableVersionChangeEvent(CTypeLogModelGroupColumn column) {
		this.column = column;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<HbaseTableVersionChangeHanlder> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(HbaseTableVersionChangeHanlder handler) {
		handler.onCheckVersionChange(this);
	}

	public CTypeLogModelGroupColumn getColumn() {
		return column;
	}

}
