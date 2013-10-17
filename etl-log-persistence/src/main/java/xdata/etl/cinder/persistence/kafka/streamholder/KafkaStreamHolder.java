/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.kafka.streamholder;

import kafka.consumer.KafkaStream;
import kafka.message.Message;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;

/**
 * @author XuehuiHe
 * @date 2013年10月16日
 */
public abstract class KafkaStreamHolder implements Runnable {
	private final KafkaStream<Message> stream;
	private final KafkaTopic topic;

	protected KafkaStreamHolder(KafkaStream<Message> stream,
			KafkaTopic topic) {
		this.stream = stream;
		this.topic = topic;
	}

	public KafkaStream<Message> getStream() {
		return stream;
	}

	public KafkaTopic getTopic() {
		return topic;
	}
}
