/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.kafka.streamholder;

import javax.annotation.Resource;

import kafka.consumer.KafkaStream;
import kafka.message.Message;

import org.springframework.stereotype.Service;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年10月16日
 */
@Service("ottKafkaStreamHolderFactory")
public class OTTKafkaStreamHolderFactory implements KafkaStreamHolderFactory {
	@Resource(name = "fixedModelFactory")
	private KafkaStreamHolderFactory fixedModelFactory;

	@Override
	public KafkaStreamHolder createHolder(KafkaStream<Message> stream,
			KafkaTopic topic) {
		if (topic instanceof KafkaTopicFixedModelVersion) {
			return fixedModelFactory.createHolder(stream, topic);
		}
		// TODO
		return null;
	}

}
