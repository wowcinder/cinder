/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.persistence.kafka.meta;

import java.util.List;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

/**
 * @author XuehuiHe
 * @date 2013年9月25日
 */
public interface KafkaMetaService {
	LogModelVersion<?> getLogModelVersion(String model, String version);

	KafkaWatchDog findWatchDog(Integer id);

	List<KafkaWatchDogTopicSetting> getAllTopicSettings(KafkaWatchDog dog);

}
