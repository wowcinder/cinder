/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.combox;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.combox.AddEnableComboBox;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.editor.LogModelEditor;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class LogModelCombox extends AddEnableComboBox<LogModel> {

	/**
	 * @param store
	 * @param labelProvider
	 * @param editor
	 */
	public LogModelCombox() {
		super(new ListStore<LogModel>(PropertyUtils.LogModelProperty.key()),
				new LabelProvider<LogModel>() {
					@Override
					public String getLabel(LogModel item) {
						return item.getName();
					}
				}, new LogModelEditor());
	}

	@Override
	protected LogModel createAddItem() {
		LogModel addItem = new LogModel();
		addItem.setId(-1);
		addItem.setName("添加...");
		return addItem;
	}

	@Override
	protected boolean isAddItem(LogModel selectItem) {
		return selectItem.getId() == -1;
	}

	@Override
	protected LogModel newComboxInstance() {
		return new LogModel();
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		RpcServiceUtils.LogModelMetaRpcService
				.getLogModels(new RpcAsyncCallback<List<LogModel>>() {
					@Override
					public void _onSuccess(List<LogModel> t) {
						getStore().clear();
						getStore().addAll(t);
						getStore().add(getAddItem());
					}
				});
	}
}
