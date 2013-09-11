package xdata.etl.cinder.gwt.client.ui.user.window;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.common.window.FixedWindow;
import xdata.etl.cinder.gwt.client.ui.user.grid.AuthorizeGrid;
import xdata.etl.cinder.gwt.client.ui.user.window.ShowAuthorizesSelectorWindowEvent.ShowAuthorizesSelectorWindowEventHandler;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.shared.entity.authorize.Authorize;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class AuthorizesSelectorWindow extends FixedWindow implements
		ShowAuthorizesSelectorWindowEventHandler {
	private AuthorizeGrid grid;
	private ShowAuthorizesSelectorWindowEvent currEvent;

	public AuthorizesSelectorWindow() {
		grid = new AuthorizeGrid(new GridConfig(true, false));
		grid.setHeight(300);
		TextButton addBt = new TextButton("添加");

		setWidget(grid);
		addButton(addBt);
		setButtonAlign(BoxLayoutPack.CENTER);

		addBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				currEvent.getCallback().call(
						getGrid().getSelectionModel().getSelectedItems());
				hide();
			}
		});
		
		addHandler(this, ShowAuthorizesSelectorWindowEvent.TYPE);
		
		setHeadingText("添加权限:");
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				getGrid().mask("加载中...");
				RpcServiceUtils.AuthorizeRpcService
						.getAllocatenbeAuthorizes(new RpcAsyncCallback<List<Authorize>>() {
							@Override
							public void _onSuccess(List<Authorize> t) {
								getGrid().getStore().replaceAll(t);
							}

							@Override
							public void post() {
								super.post();
								getGrid().unmask();
							}
						});
			}
		});

	}

	public AuthorizeGrid getGrid() {
		return grid;
	}

	@Override
	public void dealEvent(ShowAuthorizesSelectorWindowEvent event) {
		this.currEvent = event;
		getGrid().getSelectionModel().deselect(getGrid().getSelectionModel().getSelectedItems());
		show();
	}

	public void fireShowEvent(ShowAuthorizesSelectorWindowEvent event) {
		fireEvent(event);
	}

}
