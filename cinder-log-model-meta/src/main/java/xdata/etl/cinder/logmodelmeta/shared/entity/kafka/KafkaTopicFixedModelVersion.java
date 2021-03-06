/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.kafka;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Entity
@Table(name = "kafka_topic_fixed_model_version")
public class KafkaTopicFixedModelVersion extends KafkaTopic {
	private static final long serialVersionUID = -2542549323037087469L;
	private LogModelVersion<?> version;

	public KafkaTopicFixedModelVersion() {
	}

	@ManyToOne
	public LogModelVersion<?> getVersion() {
		return version;
	}

	public void setVersion(LogModelVersion<?> version) {
		this.version = version;
	}
}
