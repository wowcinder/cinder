/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.combox;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.combox.AddEnableComboBox;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.editor.JsonLogModelEditor;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModel;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class JsonLogModelCombox extends AddEnableComboBox<JsonLogModel> {

	/**
	 * @param store
	 * @param labelProvider
	 * @param editor
	 */
	public JsonLogModelCombox() {
		super(new ListStore<JsonLogModel>(
				PropertyUtils.JsonLogModelProperty.key()),
				new LabelProvider<JsonLogModel>() {
					@Override
					public String getLabel(JsonLogModel item) {
						return item.getName();
					}
				}, new JsonLogModelEditor());
	}

	@Override
	protected JsonLogModel createAddItem() {
		JsonLogModel addItem = new JsonLogModel();
		addItem.setId(-1);
		addItem.setName("添加...");
		return addItem;
	}

	@Override
	protected boolean isAddItem(JsonLogModel selectItem) {
		return selectItem.getId() == -1;
	}

	@Override
	protected JsonLogModel newComboxInstance() {
		return new JsonLogModel();
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		RpcServiceUtils.JsonLogModelMetaRpcService
				.getLogModels(new RpcAsyncCallback<List<JsonLogModel>>() {
					@Override
					public void _onSuccess(List<JsonLogModel> t) {
						getStore().clear();
						getStore().addAll(t);
						getStore().add(getAddItem());
					}
				});
	}
}
