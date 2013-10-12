/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@RemoteServiceRelativePath("rpc/kafka.rpc")
public interface KafkaRpcService extends RemoteService {
	List<LogModelVersion<?>> getLogModelVersions() throws SharedException,
			ConstraintViolationException;

	List<KafkaTopic> getTopics() throws SharedException,
			ConstraintViolationException;

	KafkaTopic saveKafkaTopic(KafkaTopic topic) throws SharedException,
			ConstraintViolationException;

	KafkaTopic updateKafkaTopic(KafkaTopic topic) throws SharedException,
			ConstraintViolationException;

	void deleteKafkaTopics(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<KafkaTopic> pagingKafkaTopic(EtlPagingLoadConfigBean config)
			throws SharedException, ConstraintViolationException;

	KafkaWatchDog saveKafkaWatchDog(KafkaWatchDog dog) throws SharedException,
			ConstraintViolationException;

	KafkaWatchDog updateKafkaWatchDog(KafkaWatchDog dog)
			throws SharedException, ConstraintViolationException;

	void deleteKafkaWatchDogs(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<KafkaWatchDog> pagingKafkaWatchDog(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException;

	KafkaWatchDogTopicSetting saveKafkaWatchDogTopicSetting(
			KafkaWatchDogTopicSetting setting) throws SharedException,
			ConstraintViolationException;

	KafkaWatchDogTopicSetting updateKafkaWatchDogTopicSetting(
			KafkaWatchDogTopicSetting setting) throws SharedException,
			ConstraintViolationException;

	void deleteKafkaWatchDogTopicSettings(List<Integer> ids)
			throws SharedException, ConstraintViolationException;

	List<KafkaWatchDogTopicSetting> getKafkaWatchDogTopicSettings(
			Integer watchDogId) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<KafkaWatchDog> pagingKafkaWatchDogStatus(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException;

	List<KafkaWatchDogTopicSetting> getKafkaWatchDogTopicSettingStatuss(
			Integer watchDogId) throws SharedException,
			ConstraintViolationException;

	ValidationSupport dummy();
}
