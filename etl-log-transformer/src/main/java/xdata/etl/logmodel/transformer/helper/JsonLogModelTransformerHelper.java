/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.logmodel.transformer.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.logmodel.transformer.LogModelTransformer;
import xdata.etl.logmodel.transformer.TypeParser;
import xdata.etl.logmodel.transformer.exception.LogTransformException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author XuehuiHe
 * @date 2013年9月25日
 */
public class JsonLogModelTransformerHelper {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JsonLogModelTransformerHelper.class);

	private static Gson gson;
	static {
		GsonBuilder gb = new GsonBuilder();
		gson = gb.create();
	}
	/**
	 * json的数据
	 */
	private final Object data;
	/**
	 * json的结构
	 */
	private final JsonArrayMap jsonMap;
	/**
	 * records
	 */
	private final Map<String, List<HbaseRecord<String>>> recordMap;
	/**
	 * log的stamp
	 */
	private final Date stamp;

	private final String raw;

	@SuppressWarnings("unchecked")
	public JsonLogModelTransformerHelper(String raw, JsonArrayMap jsonMap) {
		this.raw = raw;
		int index = raw.indexOf('#');
		if (index == -1) {
			String msg = "stamp not found\traw:" + getRaw();
			LOGGER.warn(msg);
			throw new LogTransformException(msg);
		}
		String stampStr = raw.substring(0, index);
		raw = raw.substring(index + 1);
		this.stamp = LogModelTransformer.getStamp(stampStr);
		raw = raw.replaceAll("\\[\\]", "null");
		Object tempData = null;
		try {
			tempData = gson.fromJson(raw, Object.class);
		} catch (Exception e) {
			String msg = "Json Syntax Exception\traw:" + getRaw();
			LOGGER.warn(msg);
			throw new LogTransformException(msg, e);
		}
		if (tempData instanceof Map) {
			dealRequestURL((Map<String, Object>) tempData);
		} else if (tempData instanceof Collection) {
			for (Object item : (Collection<?>) tempData) {
				if (item instanceof Map) {
					dealRequestURL((Map<String, Object>) item);
				}
			}
		}
		data = tempData;
		this.jsonMap = jsonMap;
		this.recordMap = new HashMap<String, List<HbaseRecord<String>>>();
	}

	private void dealRequestURL(Map<String, Object> tempData) {
		Object requestObject = tempData.get("request");
		if (requestObject != null && requestObject instanceof String) {
			String request = (String) requestObject;
			if (request.length() > 0) {
				tempData.put("request", splitQuery(request));
			}
		}
	}

	public Map<String, List<HbaseRecord<String>>> analyze() {
		LOGGER.debug("analyze:\t" + getRaw());
		analyzeJson(data, jsonMap, null);
		return recordMap;
	}

	/**
	 * Fixed Array only one-item:Array show as a Object
	 * 
	 * @param data
	 * @param jsonMap
	 * @param stamp
	 * @param parent
	 */
	@SuppressWarnings("unchecked")
	private void analyzeJson(Object data, JsonArrayMap jsonMap,
			HbaseRecord<String> parent) {
		if (data == null) {
			return;
		}
		if (data instanceof Collection) {
			Collection<?> list = (Collection<?>) data;
			int i = 1;
			for (Object item : list) {
				if (!(item instanceof Map)) {
					continue;
				}
				Map<String, Object> itemData = (Map<String, Object>) item;
				analyzeJsonItem(itemData, jsonMap, parent, i);
				i++;
			}
		} else if (data instanceof Map) {
			Map<String, Object> itemData = (Map<String, Object>) data;
			analyzeJsonItem(itemData, jsonMap, parent, 1);
		}
	}

	private void analyzeJsonItem(Map<String, Object> itemData,
			JsonArrayMap jsonMap, HbaseRecord<String> parent, Integer index) {
		if (itemData == null) {
			return;
		}
		HbaseRecord<String> record = new HbaseRecord<String>(stamp, jsonMap
				.getHbaseTableVersion().getVersion());
		putInRecordMap(jsonMap.getHbaseTableVersion().getTable().getName(),
				record);
		String key = null;
		if (parent == null) {
			key = LogModelTransformer.generateKey(stamp);
		} else {
			key = LogModelTransformer.generateKey(parent.getKey(), index);
		}
		record.setKey(key);
		initRecord(itemData, jsonMap, record);
		LOGGER.debug("new record:" + key + ",size:" + record.getData().size());
	}

	@SuppressWarnings("unchecked")
	private void initRecord(Map<String, Object> data,
			Map<String, Object> jsonMap, HbaseRecord<String> record) {
		for (Entry<String, Object> entry : jsonMap.entrySet()) {
			String k = entry.getKey();
			if (!data.containsKey(k)) {
				continue;
			}
			Object v = entry.getValue();
			if (v instanceof JsonArrayMap) {
				analyzeJson(data.get(k), (JsonArrayMap) v, record);
			} else if (v instanceof Map) {
				if (data.get(k) instanceof Map) {
					initRecord((Map<String, Object>) data.get(k),
							(Map<String, Object>) v, record);
				}
			} else if (v instanceof HbaseTableColumn) {// HbaseTableColumn
				HbaseTableColumn hbaseColumn = ((HbaseTableColumn) v);
				if (data.get(k) != null) {
					Object o = null;
					try {
						o = TypeParser.parse(data.get(k), hbaseColumn.getType()
								.getClazz());
					} catch (Exception e) {
						LOGGER.warn("parse wrong:str:o:"
								+ data.get(k)
								+ ","
								+ data.get(k).getClass().getName()
								+ ",table:"
								+ hbaseColumn.getVersion().getTable().getName()
								+ ",version:"
								+ hbaseColumn.getVersion().getVersion()
								+ ",column:"
								+ hbaseColumn.getName()
								+ ",type:"
								+ hbaseColumn.getType().getClazz()
										.getSimpleName());
					}
					if (o != null) {
						record.getData().put(hbaseColumn.getName(), o);
					}
				}
			} else {
				LOGGER.debug(v.getClass().getName()
						+ " no have setting to analyze");
			}
		}
	}

	private void putInRecordMap(String name, HbaseRecord<String> record) {
		if (!recordMap.containsKey(name)) {
			recordMap.put(name, new ArrayList<HbaseRecord<String>>());
		}
		recordMap.get(name).add(record);
	}

	public static Map<String, String> splitQuery(String query) {
		Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			String key;
			String value;
			try {
				key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
				value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				continue;
			}

			if ("null".equals(value)) {
				value = null;
			}
			query_pairs.put(key, value);
		}
		return query_pairs;
	}

	public String getRaw() {
		return raw;
	}

}
