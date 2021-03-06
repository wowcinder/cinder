/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.hbasemeta.combox;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.combox.AddEnableComboBox;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.editor.HbaseTableEditor;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public class HbaseTableCombox extends AddEnableComboBox<HbaseTable> {

	/**
	 * @param store
	 * @param labelProvider
	 * @param editor
	 */
	public HbaseTableCombox() {
		super(
				new ListStore<HbaseTable>(
						PropertyUtils.HbaseTableProperty.key()),
				new LabelProvider<HbaseTable>() {
					@Override
					public String getLabel(HbaseTable item) {
						return item.getName();
					}
				}, new HbaseTableEditor());
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RpcServiceUtils.HbaseMetaRpcService
						.getHbaseTablesForCombox(new RpcAsyncCallback<List<HbaseTable>>() {

							@Override
							public void _onSuccess(List<HbaseTable> t) {
								getStore().clear();
								getStore().addAll(t);
								getStore().add(getAddItem());
							}
						});
			}
		});
	}

	@Override
	protected HbaseTable createAddItem() {
		HbaseTable addItem = new HbaseTable();
		addItem.setId(-1);
		addItem.setName("添加...");
		return addItem;
	}

	@Override
	protected boolean isAddItem(HbaseTable selectItem) {
		return selectItem.getId() == -1;
	}

	@Override
	protected HbaseTable newComboxInstance() {
		return new HbaseTable();
	}

}
