/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.server.rpc;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotations;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import xdata.etl.cinder.gwt.client.service.KafkaRpcService;
import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;
import xdata.etl.cinder.server.AuthorizeNames.AuthorizeAnnotationNamesForKafka;
import xdata.etl.cinder.service.SimpleService;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Service
@AuthorizeGroupAnnotation(value = AuthorizeAnnotationNamesForKafka.GROUP)
public class KafkaRpcServiceImpl implements KafkaRpcService {
	@Autowired
	private SimpleService simpleService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@AuthorizeAnnotations(value = {
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_TOPIC),
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_TOPIC),
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.UPDATE_TOPIC) })
	public List<LogModelVersion<?>> getLogModelVersions()
			throws SharedException, ConstraintViolationException {
		return (List) simpleService.get(LogModelVersion.class);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_TOPIC)
	public KafkaTopic saveKafkaTopic(KafkaTopic topic) throws SharedException,
			ConstraintViolationException {
		return simpleService.save(topic);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.UPDATE_TOPIC)
	public KafkaTopic updateKafkaTopic(KafkaTopic topic)
			throws SharedException, ConstraintViolationException {
		return simpleService.update(topic);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.DELETE_TOPIC)
	public void deleteKafkaTopics(List<Integer> ids) throws SharedException,
			ConstraintViolationException {
		simpleService.delete(KafkaTopic.class, ids);

	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_TOPIC)
	public PagingLoadResult<KafkaTopic> pagingKafkaTopic(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(KafkaTopic.class, config);
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_WATCH_DOG)
	public KafkaWatchDog saveKafkaWatchDog(KafkaWatchDog dog)
			throws SharedException, ConstraintViolationException {
		return simpleService.save(dog);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.UPDATE_WATCH_DOG)
	public KafkaWatchDog updateKafkaWatchDog(KafkaWatchDog dog)
			throws SharedException, ConstraintViolationException {
		return simpleService.update(dog);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.DELETE_WATCH_DOG)
	public void deleteKafkaWatchDogs(List<Integer> ids) throws SharedException,
			ConstraintViolationException {
		simpleService.delete(KafkaWatchDog.class, ids);

	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_WATCH_DOG)
	public PagingLoadResult<KafkaWatchDog> pagingKafkaWatchDog(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(KafkaWatchDog.class, config);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_TOPIC_SETTING)
	public KafkaWatchDogTopicSetting saveKafkaWatchDogTopicSetting(
			KafkaWatchDogTopicSetting setting) throws SharedException,
			ConstraintViolationException {
		return simpleService.save(setting);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.UPDATE_TOPIC_SETTING)
	public KafkaWatchDogTopicSetting updateKafkaWatchDogTopicSetting(
			KafkaWatchDogTopicSetting setting) throws SharedException,
			ConstraintViolationException {
		return simpleService.update(setting);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.DELETE_TOPIC_SETTING)
	public void deleteKafkaWatchDogTopicSettings(List<Integer> ids)
			throws SharedException, ConstraintViolationException {
		simpleService.delete(KafkaWatchDogTopicSetting.class, ids);
	}

	@Override
	@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_TOPIC_SETTING)
	public List<KafkaWatchDogTopicSetting> getKafkaWatchDogTopicSettings(
			Integer watchDogId) throws SharedException,
			ConstraintViolationException {
		List<KafkaWatchDogTopicSetting> list = simpleService
				.get(KafkaWatchDogTopicSetting.class);
		List<KafkaWatchDogTopicSetting> result = new ArrayList<KafkaWatchDogTopicSetting>();
		for (KafkaWatchDogTopicSetting kafkaWatchDogTopicSetting : list) {
			if (kafkaWatchDogTopicSetting.getServer().getId()
					.equals(watchDogId)) {
				result.add(kafkaWatchDogTopicSetting);
			}
		}
		return result;
	}

	@Override
	@AuthorizeAnnotations(value = {
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.ADD_TOPIC_SETTING),
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.UPDATE_TOPIC_SETTING),
			@AuthorizeAnnotation(value = AuthorizeAnnotationNamesForKafka.QUERY_TOPIC_SETTING) })
	public List<KafkaTopic> getTopics() throws SharedException,
			ConstraintViolationException {
		return simpleService.get(KafkaTopic.class);
	}

}
