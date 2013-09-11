/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.hbasemeta;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.ui.AbstractCenterView;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.editor.HbaseTableVersionEditor;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.grid.HbaseTableVersionGrid;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.shared.annotations.MenuToken;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@MenuToken(name = "表版本管理", token = "hbase_table_version", group = "hbase_meta管理")
public class HbaseTableVersionView extends
		AbstractCenterView<HbaseTableVersion> {

	/**
	 * @param grid
	 * @param editor
	 */
	public HbaseTableVersionView() {
		super(new HbaseTableVersionGrid(new GridConfig()),
				new HbaseTableVersionEditor());
	}

	@Override
	protected HbaseTableVersion newViewInstance() {
		HbaseTableVersion v = new HbaseTableVersion();
		v.setColumns(new ArrayList<HbaseTableColumn>());
		return v;
	}

	@Override
	protected void delete(List<HbaseTableVersion> list) {
		List<Integer> ids = new ArrayList<Integer>();
		for (HbaseTableVersion hbaseTableVersion : list) {
			ids.add(hbaseTableVersion.getId());
		}
		RpcServiceUtils.HbaseMetaRpcService.deleteHbaseTableVersions(ids,
				getAsyncCallback(list));
	}
}
