package xdata.etl.cinder.gwt.client.ui.hbasemeta.combox;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.combox.AddEnableComboBox;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableColumnComboxAddEvent.HbaseTableColumnComboxAddHanlder;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.editor.HbaseTableColumnEditor;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.messages.client.DefaultMessages;

public class HbaseTableColumnCombox extends AddEnableComboBox<HbaseTableColumn>
		implements HbaseTableColumnComboxAddHanlder {
	private HbaseTableColumnComboxAddEvent currEvent;

	public HbaseTableColumnCombox() {
		super(new ListStore<HbaseTableColumn>(
				PropertyUtils.HbaseTableColumnProperty.key()),
				new LabelProvider<HbaseTableColumn>() {
					@Override
					public String getLabel(HbaseTableColumn item) {
						return item.getName();
					}
				}, new HbaseTableColumnEditor());

		addHandler(this, HbaseTableColumnComboxAddEvent.TYPE);
	}

	@Override
	protected HbaseTableColumn createAddItem() {
		HbaseTableColumn column = new HbaseTableColumn();
		column.setId(-1);
		column.setName("添加...");
		return column;
	}

	@Override
	protected boolean isAddItem(HbaseTableColumn selectItem) {
		return selectItem.getId() == -1;
	}

	@Override
	protected HbaseTableColumn newComboxInstance() {
		HbaseTableColumn column = new HbaseTableColumn();
		column.setVersion(getCurrEvent().getVersion());
		return column;
	}

	@Override
	public void onAdd(
			HbaseTableColumnComboxAddEvent hbaseTableColumnComboxAddEvent) {
		if (getCurrEvent() != null
				&& getCurrEvent().getVersion().getId() == hbaseTableColumnComboxAddEvent
						.getVersion().getId()) {
			return;

		}
		clear();
		setCurrEvent(hbaseTableColumnComboxAddEvent);
		initCombox();
	}

	private void initCombox() {
		mask(DefaultMessages.getMessages().loadMask_msg());
		RpcServiceUtils.HbaseMetaRpcService.getColumnsByVersionId(
				getCurrEvent().getVersion().getId(),
				new RpcAsyncCallback<List<HbaseTableColumn>>() {
					@Override
					public void _onSuccess(List<HbaseTableColumn> t) {
						getStore().clear();
						getStore().addAll(t);
						getStore().add(getAddItem());
					}

					@Override
					public void post() {
						super.post();
						unmask();
					}
				});
	}

	public HbaseTableColumnComboxAddEvent getCurrEvent() {
		return currEvent;
	}

	public void setCurrEvent(HbaseTableColumnComboxAddEvent currEvent) {
		this.currEvent = currEvent;
	}

	public void fireRefreshCombox(HbaseTableColumnComboxAddEvent event) {
		fireEvent(event);
	}

}
