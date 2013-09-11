/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.hbasemeta;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.ui.AbstractCenterView;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.editor.HbaseTableEditor;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.grid.HbaseTableGrid;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable;
import xdata.etl.cinder.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@MenuToken(name = "表管理", token = "hbase_table", group = "hbase_meta管理")
public class HbaseTableView extends AbstractCenterView<HbaseTable> {

	public HbaseTableView() {
		super(new HbaseTableGrid(new GridConfig()), new HbaseTableEditor());
	}

	@Override
	protected HbaseTable newViewInstance() {
		return new HbaseTable();
	}

	@Override
	protected void delete(List<HbaseTable> list) {
		List<Integer> ids = new ArrayList<Integer>();
		for (HbaseTable userGroup : list) {
			ids.add(userGroup.getId());
		}
		RpcServiceUtils.HbaseMetaRpcService.deleteHbaseTables(ids,
				getAsyncCallback(list));
	}
}
