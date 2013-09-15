package xdata.etl.cinder.gwt.client.ui.hbasemeta.combox;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.combox.AddEnableComboBox;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.editor.HbaseTableVersionEditor;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;

public class HbaseTableVersionCombox extends
		AddEnableComboBox<HbaseTableVersion> {

	public HbaseTableVersionCombox() {
		super(new ListStore<HbaseTableVersion>(
				PropertyUtils.HbaseTableVersionProperty.key()),
				new LabelProvider<HbaseTableVersion>() {
					@Override
					public String getLabel(HbaseTableVersion item) {
						if (item.getTable() == null) {
							return item.getVersion();
						}
						return item.getTable().getName() + "#"
								+ item.getVersion();
					}
				}, new HbaseTableVersionEditor());
	}

	@Override
	protected HbaseTableVersion createAddItem() {
		HbaseTableVersion version = new HbaseTableVersion();
		version.setId(-1);
		version.setVersion("添加...");
		return version;
	}

	@Override
	protected boolean isAddItem(HbaseTableVersion selectItem) {
		return selectItem.getId() == -1;
	}

	@Override
	protected HbaseTableVersion newComboxInstance() {
		return new HbaseTableVersion();
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RpcServiceUtils.HbaseMetaRpcService
						.getHbaseTableVersionsForCombox(new RpcAsyncCallback<List<HbaseTableVersion>>() {
							@Override
							public void _onSuccess(List<HbaseTableVersion> t) {
								getStore().clear();
								getStore().addAll(t);
								getStore().add(getAddItem());
							}
						});
			}
		});
	}
}
