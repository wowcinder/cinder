/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.kafka;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelBase;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
@Entity
@Table(name = "kafka_watch_dog_topic_setting", uniqueConstraints = @UniqueConstraint(columnNames = {
		"server_id", "topic_id" }))
public class KafkaWatchDogTopicSetting extends LogModelBase {
	private static final long serialVersionUID = 467001406891512377L;
	private KafkaWatchDog server;
	private KafkaTopic topic;
	private Integer threadNum;
	private KafkaWatchDogTopicSettingStatus status;

	public KafkaWatchDogTopicSetting() {
		threadNum = 1;
		status = KafkaWatchDogTopicSettingStatus.STOPED;
	}

	@ManyToOne
	@Column(nullable = false)
	public KafkaWatchDog getServer() {
		return server;
	}

	@ManyToOne
	@Column(nullable = false)
	public KafkaTopic getTopic() {
		return topic;
	}

	@Column(nullable = false, name = "thread_num")
	public Integer getThreadNum() {
		return threadNum;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	@NotNull
	public KafkaWatchDogTopicSettingStatus getStatus() {
		return status;
	}

	public void setStatus(KafkaWatchDogTopicSettingStatus status) {
		this.status = status;
	}

	public void setServer(KafkaWatchDog server) {
		this.server = server;
	}

	public void setTopic(KafkaTopic topic) {
		this.topic = topic;
	}

	public void setThreadNum(Integer threadNum) {
		this.threadNum = threadNum;
	}

	public static enum KafkaWatchDogTopicSettingStatus {
		STARTING, RUNNING, STOPPING, STOPED, EXCEPTION;
	}

}
