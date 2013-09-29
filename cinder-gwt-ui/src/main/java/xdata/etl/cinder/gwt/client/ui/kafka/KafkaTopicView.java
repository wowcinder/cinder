package xdata.etl.cinder.gwt.client.ui.kafka;

import java.util.List;

import xdata.etl.cinder.gwt.client.common.grid.GridConfig;
import xdata.etl.cinder.gwt.client.ui.AbstractCenterView;
import xdata.etl.cinder.gwt.client.ui.kafka.editor.KafkaTopicEditor;
import xdata.etl.cinder.gwt.client.ui.kafka.grid.KafkaTopicGrid;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.shared.annotations.MenuToken;

@MenuToken(name = "TOPIC", token = "kafka_topic", group = "KAFKA")
public class KafkaTopicView extends AbstractCenterView<KafkaTopic> {

	public KafkaTopicView() {
		super(new KafkaTopicGrid(new GridConfig()), new KafkaTopicEditor());
	}

	@Override
	protected KafkaTopic newViewInstance() {
		return new KafkaTopic();
	}

	@Override
	protected void delete(List<KafkaTopic> list) {
		// RpcServiceUtils.JsonLogModelMetaRpcService.deleteLogModelVersions(
		// getIds(list, new EntityToKey<KafkaTopic, Integer>() {
		//
		// @Override
		// public Integer getId(KafkaTopic m) {
		// return m.getId();
		// }
		// }), getAsyncCallback(list));
	}

}
