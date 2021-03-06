/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.combox;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.RpcAsyncCallback;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.form.ComboBox;

/**
 * @author XuehuiHe
 * @date 2013年9月29日
 */
public class LogModelVersionCombox extends ComboBox<LogModelVersion<?>> {

	/**
	 * @param store
	 * @param labelProvider
	 */
	public LogModelVersionCombox() {
		super(new ListStore<LogModelVersion<?>>(
				new ModelKeyProvider<LogModelVersion<?>>() {

					@Override
					public String getKey(LogModelVersion<?> item) {
						return item.getId() + "";
					}
				}), new LabelProvider<LogModelVersion<?>>() {

			@Override
			public String getLabel(LogModelVersion<?> item) {
				String type = "json";
				if (item instanceof CTypeLogModelVersion) {
					type = "ctype";
				}
				return type + "#" + item.getModel().getName() + "#"
						+ item.getVersion();
			}
		});
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RpcServiceUtils.KafkaRpcService
						.getLogModelVersions(new RpcAsyncCallback<List<LogModelVersion<?>>>() {
							@Override
							public void _onSuccess(List<LogModelVersion<?>> t) {
								getStore().clear();
								getStore().addAll(t);
							}
						});
			}
		});
	}

}
