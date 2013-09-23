package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.tree;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.LogModelColumnTreeDropTarget;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;

import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.messages.client.DefaultMessages;

public class CTypeLogModelColumnTreeDropTarget extends
		LogModelColumnTreeDropTarget<CTypeLogModelColumn> {

	public CTypeLogModelColumnTreeDropTarget(CTypeLogModelColumnTree tree) {
		super(tree);
	}

	@Override
	protected void move(final CTypeLogModelColumn p,
			final List<TreeStore.TreeNode<CTypeLogModelColumn>> nodes,
			final int index, CTypeLogModelColumn prev, CTypeLogModelColumn item) {
		getWidget().mask(DefaultMessages.getMessages().loadMask_msg());
		RpcServiceUtils.CTypeLogModelMetaRpcService.move(prev, p, item,
				new RpcAsyncCallback<CTypeLogModelColumn>() {
					@Override
					public void _onSuccess(CTypeLogModelColumn t) {
						TreeStore<CTypeLogModelColumn> store = getWidget()
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
	protected boolean isDropOnLeafEnabled(CTypeLogModelColumn m) {
		return m instanceof CTypeLogModelGroupColumn;
	}

}
