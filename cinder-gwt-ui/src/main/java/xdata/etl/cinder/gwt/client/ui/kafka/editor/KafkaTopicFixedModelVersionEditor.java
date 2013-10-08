/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.editor;

import xdata.etl.cinder.gwt.client.ui.kafka.combox.LogModelVersionCombox;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public class KafkaTopicFixedModelVersionEditor extends
		KafkaTopicEditor<KafkaTopicFixedModelVersion> {
	interface Driver
			extends
			SimpleBeanEditorDriver<KafkaTopicFixedModelVersion, KafkaTopicFixedModelVersionEditor> {

	}

	public KafkaTopicFixedModelVersionEditor() {
		super(GWT.<Driver> create(Driver.class));
	}

	LogModelVersionCombox version;

	@Override
	protected void _initView() {
		super._initView();
		version = new LogModelVersionCombox();
		layoutContainer.add(new FieldLabel(version, "version"), vd);
	}

}
