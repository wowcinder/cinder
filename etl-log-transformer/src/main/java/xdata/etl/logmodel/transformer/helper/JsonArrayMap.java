/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.logmodel.transformer.helper;

import java.util.HashMap;
import java.util.Map;

import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;

/**
 * json的结构
 * 
 * @author XuehuiHe
 * @date 2013年9月25日
 */
public class JsonArrayMap extends HashMap<String, Object> {
	private static final long serialVersionUID = -6925431114303751543L;
	private final HbaseTableVersion hbaseTableVersion;

	private JsonArrayMap(HbaseTableVersion hbaseTableVersion) {
		this.hbaseTableVersion = hbaseTableVersion;
	}

	public HbaseTableVersion getHbaseTableVersion() {
		return hbaseTableVersion;
	}

	public static JsonArrayMap create(JsonLogModelGroupColumn groupColumn) {
		JsonArrayMap jsonArrayMap = new JsonArrayMap(
				groupColumn.getHbaseTableVersion());
		for (JsonLogModelColumn column : groupColumn.getColumns()) {
			putInJsonMap(jsonArrayMap, column);
		}
		return jsonArrayMap;
	}

	private static String preprocessPath(String path) {
		path = path.replaceAll("\\s", "");
		path = path.replaceAll("^\\.+|\\.+$", "");
		path = path.replaceAll("\\.{2,}", "\\.");
		return path;
	}

	@SuppressWarnings("unchecked")
	public static void putInJsonMap(Map<String, Object> jsonMap,
			JsonLogModelColumn column) {
		String path = column.getPath();
		if (path == null) {
			return;
		}
		path = preprocessPath(path);
		Map<String, Object> tempMap = jsonMap;
		String[] paths = path.split("\\.");
		int length = paths.length;
		for (int i = 0; i < length - 1; i++) {
			String itemPath = paths[i];
			if (!tempMap.containsKey(itemPath)) {
				tempMap.put(itemPath, new HashMap<String, Object>());
			}
			tempMap = (HashMap<String, Object>) tempMap.get(itemPath);
		}
		String lastItemPath = paths[length - 1];
		if (column instanceof JsonLogModelSimpleColumn) {
			tempMap.put(lastItemPath,
					((JsonLogModelSimpleColumn) column).getHbaseTableColumn());
		} else {
			tempMap.put(lastItemPath, create((JsonLogModelGroupColumn) column));
		}
	}
}
