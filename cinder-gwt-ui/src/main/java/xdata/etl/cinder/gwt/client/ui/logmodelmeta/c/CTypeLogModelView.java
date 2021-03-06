/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.ui.AbstractCenterView;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.editor.CTypeLogModelEditor;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.grid.CTypeLogModelGrid;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModel;
import xdata.etl.cinder.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@MenuToken(name = "模型", token = "log_model_c", group = "CType日志模型")
public class CTypeLogModelView extends AbstractCenterView<CTypeLogModel> {

	/**
	 * @param grid
	 * @param editor
	 */
	public CTypeLogModelView() {
		super(new CTypeLogModelGrid(new GridConfig()),
				new CTypeLogModelEditor());
	}

	@Override
	protected CTypeLogModel newViewInstance() {
		return new CTypeLogModel();
	}

	@Override
	protected void delete(List<CTypeLogModel> list) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.deleteLogModels(
				getIds(list, new EntityToKey<CTypeLogModel, Integer>() {
					@Override
					public Integer getId(CTypeLogModel m) {
						return m.getId();
					}
				}), getAsyncCallback(list));
	}

}
