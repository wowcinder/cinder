package xdata.etl.cinder.gwt.client.ui;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;
import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.common.event.EditEvent;
import xdata.etl.cinder.gwt.client.common.grid.CinderGrid;
import xdata.etl.cinder.gwt.client.common.grid.HeaderBar;

import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public abstract class AbstractCenterView<M> extends SimpleCenterView {
	private final HeaderBar headerBar;
	private final CinderGrid<M> grid;
	private final CinderEditor<M> editor;

	public AbstractCenterView(CinderGrid<M> grid, final CinderEditor<M> editor) {
		this.grid = grid;
		this.editor = editor;
		headerBar = new HeaderBar();

		add(headerBar, vd);
		add(grid, mainVd);
		add(grid.getPagingToolBar(), vd);
		initAddBtHandler();
		initDelBtHandler();
		initUpdateHandler();
	}

	protected abstract M newViewInstance();

	protected abstract void delete(List<M> list);

	protected void initUpdateHandler() {
		getGrid().addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				M u = getGrid().getStore().get(event.getRowIndex());
				getEditor().fireEditEvent(
						new EditEvent<M>(u, new GwtCallBack<M>() {

							@Override
							protected void _call(M t) {
								getGrid().getStore().update(t);
							}
						}, true));
			}
		});
	}

	protected void initDelBtHandler() {
		headerBar.getDelBt().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				headerBar.getDelBt().disable();
				final List<M> list = getGrid().getSelectionModel()
						.getSelectedItems();
				if (list.size() == 0) {
					headerBar.getDelBt().enable();
				} else {
					delete(list);
				}
			}
		});
	}

	protected RpcAsyncCallback<Void> getAsyncCallback(final List<M> list) {
		return RpcAsyncCallback.dealWith(new GwtCallBack<Void>() {
			@Override
			protected void _call(Void t) {
				for (M m : list) {
					getGrid().getStore().remove(m);
				}
			}

			@Override
			protected void post() {
				super.post();
				headerBar.getDelBt().enable();
			}
		});
	}

	protected void initAddBtHandler() {
		headerBar.getAddBt().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				editor.fireEditEvent(new EditEvent<M>(newViewInstance(),
						new GwtCallBack<M>() {
							@Override
							protected void _call(M t) {
								getGrid().getStore().add(0, t);
							}
						}));
			}
		});
	}

	public HeaderBar getHeaderBar() {
		return headerBar;
	}

	public CinderGrid<M> getGrid() {
		return grid;
	}

	public CinderEditor<M> getEditor() {
		return editor;
	}

}
