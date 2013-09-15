/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.combox;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.combox.AddEnableComboBox;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.editor.CTypeLogModelEditor;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModel;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class CTypeLogModelCombox extends AddEnableComboBox<CTypeLogModel> {

	/**
	 * @param store
	 * @param labelProvider
	 * @param editor
	 */
	public CTypeLogModelCombox() {
		super(new ListStore<CTypeLogModel>(
				PropertyUtils.CTypeLogModelProperty.key()),
				new LabelProvider<CTypeLogModel>() {
					@Override
					public String getLabel(CTypeLogModel item) {
						return item.getName();
					}
				}, new CTypeLogModelEditor());
	}

	@Override
	protected CTypeLogModel createAddItem() {
		CTypeLogModel addItem = new CTypeLogModel();
		addItem.setId(-1);
		addItem.setName("添加...");
		return addItem;
	}

	@Override
	protected boolean isAddItem(CTypeLogModel selectItem) {
		return selectItem.getId() == -1;
	}

	@Override
	protected CTypeLogModel newComboxInstance() {
		return new CTypeLogModel();
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		RpcServiceUtils.CTypeLogModelMetaRpcService
				.getLogModels(new RpcAsyncCallback<List<CTypeLogModel>>() {
					@Override
					public void _onSuccess(List<CTypeLogModel> t) {
						getStore().clear();
						getStore().addAll(t);
						getStore().add(getAddItem());
					}
				});
	}
}
