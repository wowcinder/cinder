/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.ui.kafka.editor;

import xdata.etl.cinder.gwt.client.common.editor.CinderEditor;
import xdata.etl.cinder.gwt.client.util.RpcServiceUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public class KafkaWatchDogEditor extends CinderEditor<KafkaWatchDog> {
	interface Driver extends
			SimpleBeanEditorDriver<KafkaWatchDog, KafkaWatchDogEditor> {

	}

	/**
	 * @param driver
	 * @param baseHeadingText
	 */
	public KafkaWatchDogEditor() {
		super(GWT.<Driver> create(Driver.class), "watch_dog");
	}

	@Override
	protected void _update(KafkaWatchDog t) {
		RpcServiceUtils.KafkaRpcService.updateKafkaWatchDog(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _add(KafkaWatchDog t) {
		RpcServiceUtils.KafkaRpcService.saveKafkaWatchDog(t,
				getSaveOrUpdateAsyncCallback());
	}

	TextField ip;
	TextField name;
	NumberField<Integer> rmiPort;

	@Override
	protected void _initView() {
		ip = new TextField();
		layoutContainer.add(new FieldLabel(ip, "ip"), vd);

		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);

		rmiPort = new NumberField<Integer>(new IntegerPropertyEditor());
		layoutContainer.add(new FieldLabel(rmiPort, "port"), vd);

	}

}
