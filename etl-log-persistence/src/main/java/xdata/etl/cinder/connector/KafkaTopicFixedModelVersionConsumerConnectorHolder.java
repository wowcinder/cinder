/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.connector;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;
import kafka.utils.Utils;
import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.cinder.service.LogModelTransformerManager;
import xdata.etl.logmodel.transformer.LogModelTransformer;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public class KafkaTopicFixedModelVersionConsumerConnectorHolder extends
		AbstractKafkaTopicConsumerConnectorHolder {
	private final KafkaTopicFixedModelVersion topic;
	private final LogModelVersion<?> version;
	private final LogModelTransformer<?> transformer;

	/**
	 * @param kafkaClientConfig
	 * @param topicSetting
	 * @param transformerManager
	 */
	public KafkaTopicFixedModelVersionConsumerConnectorHolder(
			ConsumerConfig kafkaClientConfig,
			KafkaWatchDogTopicSetting topicSetting,
			LogModelTransformerManager transformerManager) {
		super(kafkaClientConfig, topicSetting, transformerManager);
		if (topicSetting.getTopic() instanceof KafkaTopicFixedModelVersion) {
			topic = (KafkaTopicFixedModelVersion) topicSetting.getTopic();
			version = topic.getVersion();
			transformer = getTransformerManager().getTransformer(
					getVersion().getModel().getName(),
					getVersion().getVersion());
		} else {
			topic = null;
			version = null;
			transformer = null;
			// TODO
		}
	}

	@Override
	protected void deal(KafkaStream<Message> stream) {
		try {
			ConsumerIterator<Message> it = stream.iterator();
			while (it.hasNext()) {
				MessageAndMetadata<Message> messageAndMetadata = it.next();
				ByteBuffer bb = messageAndMetadata.message().payload();
				String raw = Utils.toString(bb, getTopic().getCharset()
						.getCharset());
				Map<String, List<HbaseRecord<String>>> recordMap = getTransformer()
						.transform(raw);
				recordMap.clear();
				// TODO
			}
		} catch (Exception e) {
		}
	}

	public KafkaTopicFixedModelVersion getTopic() {
		return topic;
	}

	public LogModelVersion<?> getVersion() {
		return version;
	}

	public LogModelTransformer<?> getTransformer() {
		return transformer;
	}

}
