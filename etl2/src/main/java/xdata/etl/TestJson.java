/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.kafka.annotatins.Kafka;
import xdata.etl.kafka.transform.TerminalTransformer;
import xdata.etl.util.ClassScaner;

import com.google.gson.GsonBuilder;

/**
 * @author XuehuiHe
 * @date 2013年10月14日
 */
public class TestJson {
	public static void main(String[] args) throws Exception {
		List<Object> list = new ArrayList<Object>();
		ClassScaner cs = new ClassScaner("xdata.etl.entity");
		for (Class<?> clazz : cs.getClazzes()) {
			if (clazz.isAnnotationPresent(Kafka.class)) {
				Kafka k = clazz.getAnnotation(Kafka.class);
				if (k.transformer().equals(TerminalTransformer.class)) {
					list.add(g(clazz));
				}
			}
		}
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();
		System.out.println(gb.create().toJson(list));
	}

	public static Map<String, Object> g(Class<?> clazz) {
		Kafka k = clazz.getAnnotation(Kafka.class);
		HbaseTable t = clazz.getAnnotation(HbaseTable.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("topic", k.topic());
		map.put("table", t.name());
		List<String> orders = new ArrayList<String>();
		map.put("orders", orders);
		Map<String, Object> data = new HashMap<String, Object>();
		map.put("data", data);
		for (Field field : clazz.getDeclaredFields()) {
			int modify = field.getModifiers();
			if (!Modifier.isStatic(modify) && !Modifier.isFinal(modify)) {
				orders.add(field.getName());
				if (Collection.class.isAssignableFrom(field.getType())) {
					ParameterizedType pType = (ParameterizedType) field
							.getGenericType();
					data.put(
							field.getName(),
							getSrvMap((Class<?>) pType.getActualTypeArguments()[0]));

				} else {
					data.put(field.getName(), field.getName());
				}
			}
		}
		return map;
	}

	public static Map<String, Object> getSrvMap(Class<?> clazz) {
		HbaseTable t = clazz.getAnnotation(HbaseTable.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", t.name());
		Map<String, Object> data = new HashMap<String, Object>();
		map.put("data", data);
		List<String> orders = new ArrayList<String>();
		map.put("orders", orders);
		for (Field field : clazz.getDeclaredFields()) {
			int modify = field.getModifiers();
			if (!Modifier.isStatic(modify) && !Modifier.isFinal(modify)) {
				if (!Collection.class.isAssignableFrom(field.getType())) {
					orders.add(field.getName());
					data.put(field.getName(), field.getName());
				}
			}
		}
		return map;
	}
}
