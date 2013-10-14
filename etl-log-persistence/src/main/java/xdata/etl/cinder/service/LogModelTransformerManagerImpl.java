/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.logmodel.transformer.LogModelTransformer;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
@Service
public class LogModelTransformerManagerImpl implements
		LogModelTransformerManager {

	private static Logger logger = LoggerFactory
			.getLogger(LogModelTransformerManager.class);
	private final Map<LogModelTransformerKey, LogModelTransformer<?>> transformersMap;
	@Autowired
	private KafkaDbService dbService;

	public LogModelTransformerManagerImpl() {
		transformersMap = new HashMap<LogModelTransformerKey, LogModelTransformer<?>>();
	}

	public LogModelTransformer<?> getTransformer(String model, String version) {
		return getTransformer(new LogModelTransformerKey(model, version));
	}

	public LogModelTransformer<?> getTransformer(
			LogModelTransformerKey transformerKey) {
		if (transformersMap.containsKey(transformerKey)) {
			return transformersMap.get(transformerKey);
		} else {
			return createTransformer(transformerKey);
		}
	}

	public synchronized void removeTransformer(String model, String version) {
		removeTransformer(new LogModelTransformerKey(model, version));
	}

	public synchronized void removeTransformer(
			LogModelTransformerKey transformerKey) {
		transformersMap.remove(transformerKey);
	}

	protected synchronized LogModelTransformer<?> createTransformer(
			LogModelTransformerKey transformerKey) {
		if (transformersMap.containsKey(transformerKey)) {
			return transformersMap.get(transformerKey);
		}
		logger.info("create transformer for " + transformerKey);
		LogModelVersion<?> logModelVersion = dbService.getLogModelVersion(
				transformerKey.getModel(), transformerKey.getVersion());
		LogModelTransformer<LogModelVersion<?>> transformer = null;
		try {
			transformer = LogModelTransformer
					.createTransformer(logModelVersion);
		} catch (Exception e) {
			logger.info("create transformer for " + transformerKey
					+ ",failed,msg" + e.getMessage());
			e.printStackTrace();
		}
		transformersMap.put(transformerKey, transformer);
		return transformer;
	}

	public Map<LogModelTransformerKey, LogModelTransformer<?>> getTransformersMap() {
		return transformersMap;
	}

	public KafkaDbService getLogModelService() {
		return dbService;
	}

	public void setLogModelService(KafkaDbService logModelService) {
		this.dbService = logModelService;
	}

	public static class LogModelTransformerKey {
		private String model;
		private String version;

		public LogModelTransformerKey(String model, String version) {
			if (model == null || version == null) {
				throw new NullPointerException();
			}
			this.model = model;
			this.version = version;
		}

		public String getModel() {
			return model;
		}

		public String getVersion() {
			return version;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		@Override
		public int hashCode() {
			return getVersion().hashCode() + 3 * getModel().hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj instanceof LogModelTransformerKey) {
				LogModelTransformerKey that = (LogModelTransformerKey) obj;
				return this.getVersion().equals(that.getVersion())
						&& this.getModel().equals(that.getModel());
			}
			return false;
		}

		@Override
		public String toString() {
			return "model:" + model + ",version:" + version;
		}

	}
}
