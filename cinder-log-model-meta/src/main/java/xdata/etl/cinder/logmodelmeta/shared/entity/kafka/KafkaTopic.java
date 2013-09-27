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
	private KafkaTopicCharset charset;

	public KafkaTopic() {
		status = KafkaTopicStatus.ENABLED;
		charset = KafkaTopicCharset.UTF8;
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

	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	@NotNull
	public KafkaTopicCharset getCharset() {
		return charset;
	}

	public void setCharset(KafkaTopicCharset charset) {
		this.charset = charset;
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

	public static enum KafkaTopicCharset {
		GBK("GBK"), UTF8("UTF-8"), ISO88591("ISO-8859-1");
		private final String charset;

		private KafkaTopicCharset(String charset) {
			this.charset = charset;
		}

		public String getCharset() {
			return charset;
		}

	}
}
