package xdata.etl.cinder.gwt.client.ui.logmodelmeta.json.tree;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.LogModelColumnTreeDropTarget;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;

import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.messages.client.DefaultMessages;

public class JsonLogModelColumnTreeDropTarget extends
		LogModelColumnTreeDropTarget<JsonLogModelColumn> {

	public JsonLogModelColumnTreeDropTarget(JsonLogModelColumnTree tree) {
		super(tree);
	}

	@Override
	protected void move(final JsonLogModelColumn p,
			final List<TreeStore.TreeNode<JsonLogModelColumn>> nodes,
			final int index, JsonLogModelColumn prev, JsonLogModelColumn item) {
		getWidget().mask(DefaultMessages.getMessages().loadMask_msg());
		RpcServiceUtils.JsonLogModelMetaRpcService.move(prev, p, item,
				new RpcAsyncCallback<JsonLogModelColumn>() {
					@Override
					public void _onSuccess(JsonLogModelColumn t) {
						TreeStore<JsonLogModelColumn> store = getWidget()
								.getStore();
						update(p, nodes, index);
						store.update(t);
					}

					@Override
					public void post() {
						super.post();
						getWidget().unmask();
					}
				});
	}

	@Override
	protected boolean isDropOnLeafEnabled(JsonLogModelColumn m) {
		return m instanceof JsonLogModelGroupColumn;
	}

}
