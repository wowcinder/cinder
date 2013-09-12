/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.logmodelmeta;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.cinder.gwt.client.common.GwtCallBack;
import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.common.event.EditEvent;
import xdata.etl.cinder.gwt.client.ui.SimpleCenterView;
import xdata.etl.cinder.gwt.client.ui.logmodelmeta.editor.LogModelColumnJsonEditor;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.LogModelColumnContentJson;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.LogModelColumnJson;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;
import xdata.etl.cinder.shared.annotations.MenuToken;

import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@MenuToken(name = "test", token = "test", group = "test")
public class TestView extends SimpleCenterView {
	public TestView() {
		TextButton tb = new TextButton("测试");
		add(tb);

		final LogModelColumnJsonEditor editor = new LogModelColumnJsonEditor();
		tb.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				LogModel logModel = new LogModel();
				logModel.setId(7);

				final LogModelVersion version = new LogModelVersion();
				version.setVersion("0.0");
				version.setMtype(LogModelType.JSON_TYPE);
				version.setModel(logModel);
				version.setDesc("test");

				LogModelColumnContentJson contentJson = new LogModelColumnContentJson();
				contentJson.setPath("sjdlfjsljd");

				LogModelColumnJson columnJson = new LogModelColumnJson();
				columnJson.setVersion(version);
				columnJson.setContent(contentJson);

				editor.fireEditEvent(new EditEvent<LogModelColumnJson>(
						columnJson, new GwtCallBack<LogModelColumnJson>() {

							@Override
							protected void _call(LogModelColumnJson t) {
								LogModelColumn p = new LogModelColumn();
								p.setVersion(t.getVersion());
								p.setContent(t.getContent());
								p.setMtype(t.getMtype());
								
								List<LogModelColumn> list = new ArrayList<LogModelColumn>();
								list.add(p);
								version.setColumns(list);
								RpcServiceUtils.LogModelMetaRpcService
										.saveVersion(
												version,
												new RpcAsyncCallback<LogModelVersion>() {

													@Override
													public void _onSuccess(
															LogModelVersion t) {

													}
												});
								// Info.display("测试",
								// ((LogModelColumnContentJson) t
								// .getContent()).getPath());
							}
						}));

			}
		});
	}
}
