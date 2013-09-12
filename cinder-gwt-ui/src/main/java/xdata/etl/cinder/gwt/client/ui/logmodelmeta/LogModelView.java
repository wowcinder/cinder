/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.ui.AbstractCenterView;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.editor.LogModelEditor;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.grid.LogModelGrid;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@MenuToken(name = "日志模型", token = "log_model", group = "kafka日志模型")
public class LogModelView extends AbstractCenterView<LogModel> {

	/**
	 * @param grid
	 * @param editor
	 */
	public LogModelView() {
		super(new LogModelGrid(new GridConfig()), new LogModelEditor());
	}

	@Override
	protected LogModel newViewInstance() {
		return new LogModel();
	}

	@Override
	protected void delete(List<LogModel> list) {
		RpcServiceUtils.LogModelMetaRpcService.deleteLogModels(
				getIds(list, new EntityToKey<LogModel, Integer>() {
					@Override
					public Integer getId(LogModel m) {
						return m.getId();
					}
				}), getAsyncCallback(list));
	}

}
