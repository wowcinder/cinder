/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.logmodel.transformer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月24日
 */
public abstract class LogModelTransformer<Version extends LogModelVersion<?>> {
	private final Version version;

	protected LogModelTransformer(Version version) {
		this.version = version;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LogModelTransformer<LogModelVersion<?>> createTransformer(
			LogModelVersion<?> version) {
		if (version instanceof CTypeLogModelVersion) {
			return (LogModelTransformer) new CTypeLogModelTransformer(
					(CTypeLogModelVersion) version);
		} else if (version instanceof JsonLogModelVersion) {
			return (LogModelTransformer) new JsonLogModelTransformer(
					(JsonLogModelVersion) version);
		}
		return null;
	}

	public abstract Map<String, List<HbaseRecord<String>>> transform(String log);

	public static Date getStamp(String stamp) {
		if (!stamp.matches("^\\d+$")) {
			return null;
		}
		try {
			Long t = Long.parseLong(stamp) * 1000;
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(t);
			return c.getTime();
		} catch (Exception e) {
		}
		return null;
	}

	public static String generateKey(Date stamp) {
		if (stamp == null) {
			stamp = new Date();
		}
		return (10000000000000l - stamp.getTime()) / 1000 + "_" + getRandom();
	}

	public static String generateKey(String parentKey, Integer index) {
		if (index < 10) {
			return parentKey + "_00" + index;
		} else if (index < 100) {
			return parentKey + "_0" + index;
		} else {
			return parentKey + "_" + index;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T parse(String str, Class<T> clazz) {
		if (clazz.equals(Short.class)) {
			return (T) (Short) Short.parseShort(str);
		} else if (clazz.equals(Integer.class)) {
			return (T) (Integer) Integer.parseInt(str);
		} else if (clazz.equals(Long.class)) {
			return (T) (Long) Long.parseLong(str);
		} else if (clazz.equals(Float.class)) {
			return (T) (Float) Float.parseFloat(str);
		} else if (clazz.equals(Double.class)) {
			return (T) (Double) Double.parseDouble(str);
		} else if (clazz.equals(Character.class)) {
			if (str.length() > 0) {
				return (T) (Character) str.charAt(0);
			}
			return null;
		} else if (clazz.equals(Boolean.class)) {
			return (T) (Boolean) Boolean.parseBoolean(str);
		} else if (clazz.equals(Date.class)) {
			// TODO
			return null;
		} else {
			return (T) str;
		}
	}

	/**
	 * @return 10000~99999
	 */
	protected static Long getRandom() {
		return (long) Math.ceil(10000 + 90000 * Math.random());
	}

	public Version getVersion() {
		return version;
	}
}
