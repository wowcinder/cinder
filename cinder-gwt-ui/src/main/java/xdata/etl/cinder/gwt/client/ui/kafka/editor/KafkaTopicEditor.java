/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.editor;

import xdata.etl.cinder.gwt.client.common.combox.EnumComboBox;
import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicStatus;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月29日
 */
public class KafkaTopicEditor<T extends KafkaTopic> extends CinderEditor<T> {

	public static KafkaTopicFixedModelVersionEditor createFixedModeVersionEditor() {
		return new KafkaTopicFixedModelVersionEditor();
	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public KafkaTopicEditor(
			SimpleBeanEditorDriver<T, ? extends CinderEditor<T>> driver) {
		super(driver, "kafka主题");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void _update(KafkaTopic t) {
		RpcServiceUtils.KafkaRpcService.updateKafkaTopic(t,
				(AsyncCallback<KafkaTopic>) getSaveOrUpdateAsyncCallback());
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void _add(KafkaTopic t) {
		RpcServiceUtils.KafkaRpcService.saveKafkaTopic(t,
				(AsyncCallback<KafkaTopic>) getSaveOrUpdateAsyncCallback());
	}

	TextField name;
	EnumComboBox<KafkaTopicCharset> charset;
	EnumComboBox<KafkaTopicStatus> status;

	@Override
	protected void _initView() {
		name = new TextField();
		charset = new EnumComboBox<KafkaTopic.KafkaTopicCharset>(
				KafkaTopicCharset.values());
		status = new EnumComboBox<KafkaTopic.KafkaTopicStatus>(
				KafkaTopicStatus.values());

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(charset, "charset"), vd);
		layoutContainer.add(new FieldLabel(status, "status"), vd);
	}

}
