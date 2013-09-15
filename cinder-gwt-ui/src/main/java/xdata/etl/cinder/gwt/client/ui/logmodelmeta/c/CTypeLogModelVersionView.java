package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.ui.AbstractCenterView;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.editor.CTypeLogModelVersionEditor;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.grid.CTypeLogModelVersionGrid;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.shared.annotations.MenuToken;

@MenuToken(name = "模型版本", token = "log_model_version_c", group = "CType日志模型")
public class CTypeLogModelVersionView extends
		AbstractCenterView<CTypeLogModelVersion> {

	public CTypeLogModelVersionView() {
		super(new CTypeLogModelVersionGrid(new GridConfig()),
				new CTypeLogModelVersionEditor());
	}

	@Override
	protected CTypeLogModelVersion newViewInstance() {
		return new CTypeLogModelVersion();
	}

	@Override
	protected void delete(List<CTypeLogModelVersion> list) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.deleteLogModelVersions(
				getIds(list, new EntityToKey<CTypeLogModelVersion, Integer>() {

					@Override
					public Integer getId(CTypeLogModelVersion m) {
						return m.getId();
					}
				}), getAsyncCallback(list));
	}

}
