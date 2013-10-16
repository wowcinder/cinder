/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;

import xdata.etl.entity.apachelog.ApacheLog;
import xdata.etl.hbase.annotatins.HbaseColumn;

/**
 * @author XuehuiHe
 * @date 2013年10月16日
 */
public class TestJson2 {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("topic", "apache");
		map.put("table", "msg_apache_log");

		Map<String, String> data = new HashMap<String, String>();
		map.put("data", data);

		for (Field field : ApacheLog.class.getDeclaredFields()) {
			int mod = field.getModifiers();
			if (!Modifier.isStatic(mod)) {
				HbaseColumn hbaseColumn = field
						.getAnnotation(HbaseColumn.class);
				data.put(field.getName(), hbaseColumn.name());
			}
		}
		
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();
		System.out.println(gb.create().toJson(map));
	}
}
