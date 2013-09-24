/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.json;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.ui.AbstractCenterView;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.editor.JsonLogModelEditor;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.grid.JsonLogModelGrid;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModel;
import xdata.etl.cinder.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@MenuToken(name = "模型", token = "log_model_json", group = "Json日志模型")
public class JsonLogModelView extends AbstractCenterView<JsonLogModel> {

	/**
	 * @param grid
	 * @param editor
	 */
	public JsonLogModelView() {
		super(new JsonLogModelGrid(new GridConfig()), new JsonLogModelEditor());
	}

	@Override
	protected JsonLogModel newViewInstance() {
		return new JsonLogModel();
	}

	@Override
	protected void delete(List<JsonLogModel> list) {
		RpcServiceUtils.JsonLogModelMetaRpcService.deleteLogModels(
				getIds(list, new EntityToKey<JsonLogModel, Integer>() {
					@Override
					public Integer getId(JsonLogModel m) {
						return m.getId();
					}
				}), getAsyncCallback(list));
	}

}
