package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.tree;

import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableVersionCombox;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;

public class CTypeLogModelColumnTree extends Tree<CTypeLogModelColumn, String> {
	private final HbaseTableVersionCombox hbaseTableVersion;

	public CTypeLogModelColumnTree(TreeStore<CTypeLogModelColumn> store,
			HbaseTableVersionCombox hbaseTableVersion) {
		super(store, new ValueProvider<CTypeLogModelColumn, String>() {

			@Override
			public String getValue(CTypeLogModelColumn column) {
				String prefix = "";
				if (column instanceof CTypeLogModelGroupColumn) {
					prefix = "group:";
				}
				return prefix + column.getName();
			}

			@Override
			public void setValue(CTypeLogModelColumn object, String value) {
			}

			@Override
			public String getPath() {
				return null;
			}
		});
		setIconProvider(new IconProvider<CTypeLogModelColumn>() {
			@Override
			public ImageResource getIcon(CTypeLogModelColumn model) {
				ImageResource style = null;
				TreeStyle ts = getStyle();
				if (!isLeaf(model)) {
					if (isExpanded(model)) {
						style = ts.getNodeOpenIcon() != null ? ts
								.getNodeOpenIcon() : appearance.openNodeIcon();
					} else {
						style = ts.getNodeCloseIcon() != null ? ts
								.getNodeCloseIcon() : appearance
								.closeNodeIcon();
					}
				} else if (model instanceof CTypeLogModelGroupColumn) {
					style = ts.getNodeCloseIcon() != null ? ts
							.getNodeCloseIcon() : appearance.closeNodeIcon();
				} else {
					style = ts.getLeafIcon();
				}
				return style;
			}
		});
		this.hbaseTableVersion = hbaseTableVersion;
		initDND();
		getElement().getStyle().setTextAlign(TextAlign.LEFT);
		setWidth(300);
		getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setContextMenu(new ColumnTreeMenu(this));
	}

	private void initDND() {
		new TreeDragSource<CTypeLogModelColumn>(this);
		TreeDropTarget<CTypeLogModelColumn> target = new TreeDropTarget<CTypeLogModelColumn>(
				this);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.BOTH);
	}

	public HbaseTableVersion getHbaseTableVersion() {
		return hbaseTableVersion.getValue();
	}

}