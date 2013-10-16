/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.kafka.streamholder;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;
import kafka.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.hbase.lazy.LazyHTableService;
import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;
import xdata.etl.cinder.service.LogModelTransformerManager;
import xdata.etl.logmodel.transformer.LogModelTransformer;

/**
 * @author XuehuiHe
 * @date 2013年10月16日
 */
@Service("fixedModelFactory")
public class FixedModelVersionKafkaStreamHolderFactory implements
		KafkaStreamHolderFactory {
	private static Logger logger = LoggerFactory
			.getLogger(KafkaStreamHolderFactory.class);

	@Autowired
	private LogModelTransformerManager transformerManager;
	@Autowired
	private LazyHTableService lazyHTableService;

	@Override
	public KafkaStreamHolder createHolder(KafkaStream<Message> stream,
			KafkaTopic topic) {
		return new FixedModelVersionKafkaStreamHolder(stream, topic);
	}

	public LogModelTransformerManager getTransformerManager() {
		return transformerManager;
	}

	public LazyHTableService getLazyHTableService() {
		return lazyHTableService;
	}

	public void setTransformerManager(
			LogModelTransformerManager transformerManager) {
		this.transformerManager = transformerManager;
	}

	public void setLazyHTableService(LazyHTableService lazyHTableService) {
		this.lazyHTableService = lazyHTableService;
	}

	public class FixedModelVersionKafkaStreamHolder extends KafkaStreamHolder {
		private final LogModelTransformer<?> transformer;

		protected FixedModelVersionKafkaStreamHolder(
				KafkaStream<Message> kafkaStream, KafkaTopic topic) {
			super(kafkaStream, topic);
			LogModelVersion<?> version = getTopic().getVersion();
			transformer = getTransformerManager().getTransformer(
					version.getModel().getName(), version.getVersion());
		}

		@Override
		public void run() {
			try {

				ConsumerIterator<Message> it = getStream().iterator();
				while (it.hasNext()) {
					try {
						MessageAndMetadata<Message> messageAndMetadata = it
								.next();
						ByteBuffer bb = messageAndMetadata.message().payload();
						String raw = Utils.toString(bb, getTopic().getCharset()
								.getCharset());
						Map<String, List<HbaseRecord<String>>> recordMap = getTransformer()
								.transform(raw);
						getLazyHTableService().put(recordMap);
					} catch (Exception e) {
						logger.warn(e.getMessage());
						e.printStackTrace();
					}

				}

			} catch (Exception e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
			}
		}

		public KafkaTopicFixedModelVersion getTopic() {
			return (KafkaTopicFixedModelVersion) super.getTopic();
		}

		public LogModelTransformer<?> getTransformer() {
			return transformer;
		}

	}

}
