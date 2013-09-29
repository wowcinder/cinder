/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.editor;

import xdata.etl.cinder.gwt.client.common.combox.EnumComboBox;
import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.ui.kafka.combox.LogModelVersionCombox;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicStatus;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月29日
 */
public class KafkaTopicEditor extends CinderEditor<KafkaTopic> {
	interface Driver extends
			SimpleBeanEditorDriver<KafkaTopic, KafkaTopicEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public KafkaTopicEditor() {
		super(GWT.<Driver> create(Driver.class), "kafka主题");
	}

	@Override
	protected void _update(KafkaTopic t) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void _add(KafkaTopic t) {
		// TODO Auto-generated method stub

	}

	TextField name;
	EnumComboBox<KafkaTopicCharset> charset;
	EnumComboBox<KafkaTopicStatus> status;
	LogModelVersionCombox version;

	@Override
	protected void _initView() {
		name = new TextField();
		charset = new EnumComboBox<KafkaTopic.KafkaTopicCharset>(
				KafkaTopicCharset.values());
		status = new EnumComboBox<KafkaTopic.KafkaTopicStatus>(
				KafkaTopicStatus.values());
		version = new LogModelVersionCombox();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(charset, "charset"), vd);
		layoutContainer.add(new FieldLabel(status, "status"), vd);
		layoutContainer.add(new FieldLabel(version, "version"), vd);
	}

}
