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
public interface KafkaStreamHolderFactory {
	public KafkaStreamHolder createHolder(KafkaStream<Message> stream,
			KafkaTopic topic);
}
