/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.kafka;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelBase;
import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
@Entity
@Table(name = "kafka_topic")
public class KafkaTopic extends LogModelBase {
	private static final long serialVersionUID = -7632761348362494932L;
	private String name;
	private LogModelVersion<?> version;
	private List<KafkaWatchDogTopicSetting> topicSettings;
	private KafkaTopicStatus status;

	public KafkaTopic() {
		status = KafkaTopicStatus.ENABLED;
	}

	@Column(length = 100, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	@ManyToOne
	public LogModelVersion<?> getVersion() {
		return version;
	}

	@OneToMany(mappedBy = "topic", cascade = CascadeType.REMOVE)
	public List<KafkaWatchDogTopicSetting> getTopicSettings() {
		return topicSettings;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	@NotNull
	public KafkaTopicStatus getStatus() {
		return status;
	}

	public void setStatus(KafkaTopicStatus status) {
		this.status = status;
	}

	public void setTopicSettings(List<KafkaWatchDogTopicSetting> topicSettings) {
		this.topicSettings = topicSettings;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersion(LogModelVersion<?> version) {
		this.version = version;
	}

	public static enum KafkaTopicStatus {
		DISABLED, ENABLED;
	}
}
