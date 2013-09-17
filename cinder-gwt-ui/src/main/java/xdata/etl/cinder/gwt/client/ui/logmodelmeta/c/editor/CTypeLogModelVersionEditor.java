/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.editor;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.common.event.EditEvent;
import xdata.etl.cinder.gwt.client.ui.hbasemeta.combox.HbaseTableVersionCombox;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.combox.CTypeLogModelCombox;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.combox.GroupColumnHbaseTableVersionCombox;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.tree.CTypeLogModelColumnTree;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.tree.HbaseTableVersionChangeEvent;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.c.tree.HbaseTableVersionChangeEvent.HbaseTableVersionChangeHanlder;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.messages.client.DefaultMessages;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
public class CTypeLogModelVersionEditor extends
		CinderEditor<CTypeLogModelVersion> implements
		HbaseTableVersionChangeHanlder {
	interface Driver
			extends
			SimpleBeanEditorDriver<CTypeLogModelVersion, CTypeLogModelVersionEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public CTypeLogModelVersionEditor() {
		super(GWT.<Driver> create(Driver.class), "日志模型版本");
		addHandler(this, HbaseTableVersionChangeEvent.TYPE);
	}

	@Override
	protected void _update(CTypeLogModelVersion t) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.updateLogModelVersion(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(CTypeLogModelVersion t) {
		RpcServiceUtils.CTypeLogModelMetaRpcService.saveLogModelVersion(t,
				getSaveOrUpdateAsyncCallback());
	}

	CTypeLogModelCombox model;
	TextField version;
	@Path("rootNode.hbaseTableVersion")
	HbaseTableVersionCombox hbaseTableVersion;
	TextArea desc;

	CTypeLogModelColumnTree columnsTree;
	TreeStore<CTypeLogModelColumn> treeStore;
	@Ignore
	CTypeLogModelGroupColumn rootNode;

	@Override
	protected void _initView() {
		model = new CTypeLogModelCombox();
		version = new TextField();
		hbaseTableVersion = new GroupColumnHbaseTableVersionCombox() {
			@Override
			protected void checkHbaseTableColumn(HbaseTableVersion currVersion) {
				rootNode.setHbaseTableVersion(currVersion);
				HbaseTableVersionChangeEvent event = new HbaseTableVersionChangeEvent(
						rootNode);
				getParent().fireEvent(event);
			}
		};
		desc = new TextArea();
		layoutContainer.add(new FieldLabel(model, "model"), vd);
		layoutContainer.add(new FieldLabel(version, "version"), vd);
		layoutContainer.add(new FieldLabel(hbaseTableVersion,
				"hbaseTableVersion"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
		treeStore = new TreeStore<CTypeLogModelColumn>(
				new ModelKeyProvider<CTypeLogModelColumn>() {
					@Override
					public String getKey(CTypeLogModelColumn item) {
						return getColumnKey(item);
					}
				});
		columnsTree = new CTypeLogModelColumnTree(treeStore);
		columnsTree.setHeight(300);

		FieldLabel columnsTreeFieldLabel = new FieldLabel(columnsTree, "字段");
		columnsTreeFieldLabel.setLabelAlign(LabelAlign.TOP);
		layoutContainer.add(columnsTreeFieldLabel, mainVd);
	}

	protected String getColumnKey(CTypeLogModelColumn item) {
		if (item.getGroupColumn() == null) {
			return item.getName();
		} else {
			return getColumnKey(item.getGroupColumn()) + "#" + item.getName();
		}
	}

	@Override
	public void onEdit(EditEvent<CTypeLogModelVersion> event) {
		super.onEdit(event);
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				initTree();
			}
		});
	}

	@Override
	protected CTypeLogModelVersion flush() {
		CTypeLogModelVersion version = super.flush();
		CTypeLogModelGroupColumn rootNode = (CTypeLogModelGroupColumn) treeStore
				.getRootItems().get(0);
		flushTree(rootNode);
		version.setRootNode(rootNode);
		return version;
	}

	protected void flushTree(CTypeLogModelGroupColumn groupColumn) {
		List<CTypeLogModelColumn> columns = treeStore.getChildren(groupColumn);
		for (CTypeLogModelColumn column : columns) {
			column.setGroupColumn(groupColumn);
			if (column instanceof CTypeLogModelGroupColumn) {
				flushTree((CTypeLogModelGroupColumn) column);
			}
		}
		groupColumn.setColumns(columns);
	}

	protected void initTree() {
		treeStore.clear();
		final EditEvent<CTypeLogModelVersion> event = getCurrEditEvent();
		if (event.isUpdate()) {
			columnsTree.mask(DefaultMessages.getMessages().loadMask_msg());
			RpcServiceUtils.CTypeLogModelMetaRpcService
					.getLogModelVersionRootNode(event.getTarget().getId(),
							new RpcAsyncCallback<CTypeLogModelGroupColumn>() {

								@Override
								public void _onSuccess(
										CTypeLogModelGroupColumn t) {
									initRootNode(t);
								}

								@Override
								public void post() {
									super.post();
									columnsTree.unmask();
								}
							});
		} else {
			initRootNode(event.getTarget().getRootNode());
		}
	}

	protected void initRootNode(CTypeLogModelGroupColumn rootNode) {
		this.rootNode = rootNode;
		treeStore.add(rootNode);
		if (rootNode.getColumns() == null) {
			rootNode.setColumns(new ArrayList<CTypeLogModelColumn>());
		}
		for (CTypeLogModelColumn childNode : rootNode.getColumns()) {
			initRootNode(rootNode, childNode);
		}
	}

	protected void initRootNode(CTypeLogModelGroupColumn parentNode,
			CTypeLogModelColumn childNode) {
		treeStore.add(parentNode, childNode);
		if (childNode instanceof CTypeLogModelGroupColumn) {
			if (((CTypeLogModelGroupColumn) childNode).getColumns() == null) {
				((CTypeLogModelGroupColumn) childNode)
						.setColumns(new ArrayList<CTypeLogModelColumn>());
			}
			for (CTypeLogModelColumn childNode2 : ((CTypeLogModelGroupColumn) childNode)
					.getColumns()) {
				initRootNode(((CTypeLogModelGroupColumn) childNode), childNode2);
			}
		}
	}

	@Override
	public void onCheckVersionChange(HbaseTableVersionChangeEvent event) {
		columnsTree.fireEvent(event);
	}

}
