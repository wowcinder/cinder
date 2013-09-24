package xdata.etl.cinder.gwt.client.ui.logmodelmeta.json;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.ui.AbstractCenterView;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.editor.JsonLogModelVersionEditor;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.grid.JsonLogModelVersionGrid;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import xdata.etl.cinder.shared.annotations.MenuToken;

@MenuToken(name = "模型版本", token = "log_model_version_json", group = "Json日志模型")
public class JsonLogModelVersionView extends
		AbstractCenterView<JsonLogModelVersion> {

	public JsonLogModelVersionView() {
		super(new JsonLogModelVersionGrid(new GridConfig()),
				new JsonLogModelVersionEditor());
	}

	@Override
	protected JsonLogModelVersion newViewInstance() {
		return new JsonLogModelVersion();
	}

	@Override
	protected void delete(List<JsonLogModelVersion> list) {
		RpcServiceUtils.JsonLogModelMetaRpcService.deleteLogModelVersions(
				getIds(list, new EntityToKey<JsonLogModelVersion, Integer>() {

					@Override
					public Integer getId(JsonLogModelVersion m) {
						return m.getId();
					}
				}), getAsyncCallback(list));
	}

}
