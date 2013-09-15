package xdata.etl.cinder.gwt.client.ui.hbasemeta.combox;

import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableColumnComboxAddEvent.HbaseTableColumnComboxAddHanlder;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class HbaseTableColumnComboxAddEvent extends
		GwtEvent<HbaseTableColumnComboxAddHanlder> {
	public static final Type<HbaseTableColumnComboxAddHanlder> TYPE = new Type<HbaseTableColumnComboxAddHanlder>();

	public interface HbaseTableColumnComboxAddHanlder extends EventHandler {
		void onAdd(HbaseTableColumnComboxAddEvent hbaseTableColumnComboxAddEvent);
	}

	private final HbaseTableVersion version;

	public HbaseTableColumnComboxAddEvent(HbaseTableVersion version) {
		this.version = version;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<HbaseTableColumnComboxAddHanlder> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(HbaseTableColumnComboxAddHanlder handler) {
		handler.onAdd(this);
	}

	public HbaseTableVersion getVersion() {
		return version;
	}

}
