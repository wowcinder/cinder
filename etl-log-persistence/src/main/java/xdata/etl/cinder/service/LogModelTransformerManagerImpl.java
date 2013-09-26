/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	private final Map<LogModelTransformerKey, LogModelTransformer<?>> transformersMap;
	@Autowired
	private LogModelService logModelService;

	public LogModelTransformerManagerImpl() {
		transformersMap = new ConcurrentHashMap<LogModelTransformerKey, LogModelTransformer<?>>();
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
		LogModelVersion<?> logModelVersion = logModelService
				.getLogModelVersion(transformerKey.getModel(),
						transformerKey.getVersion());
		LogModelTransformer<LogModelVersion<?>> transformer = null;
		try {
			transformer = LogModelTransformer
					.createTransformer(logModelVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		transformersMap.put(transformerKey, transformer);
		return transformer;
	}

	public Map<LogModelTransformerKey, LogModelTransformer<?>> getTransformersMap() {
		return transformersMap;
	}

	public LogModelService getLogModelService() {
		return logModelService;
	}

	public void setLogModelService(LogModelService logModelService) {
		this.logModelService = logModelService;
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

	}
}
