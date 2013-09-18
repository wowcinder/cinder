package xdata.etl.kafka.transform.handler;

import xdata.etl.kafka.transform.result.KafkaTransformResult;

public interface KafkaTransformHandler {
	public final static KafkaTransformHandler EMPTY_HANDLER = new KafkaTransformHandler() {
		@Override
		public void handler(KafkaTransformResult result) {
		}
	};

	public void handler(KafkaTransformResult result);
}
