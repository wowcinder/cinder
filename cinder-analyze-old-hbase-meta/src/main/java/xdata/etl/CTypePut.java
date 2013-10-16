/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import xdata.etl.cinder.CTypePutService;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;
import xdata.etl.cinder.service.CTypeLogModelMetaService;
import xdata.etl.cinder.service.HbaseMetaService;
import xdata.etl.cinder.service.SimpleService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author XuehuiHe
 * @date 2013年10月14日
 */
public class CTypePut {

	private static ClassPathXmlApplicationContext ac;
	public static CTypeLogModelMetaService service;
	public static SimpleService simpleService;
	public static HbaseMetaService hbaseMetaService;
	private static CTypePutService putService;

	public static void main(String[] args) throws IOException {
		ac = new ClassPathXmlApplicationContext("spring-cinder-analyze.xml");
		init();
		GsonBuilder gb = new GsonBuilder();
		Gson gson = gb.create();
		List<Map<String, Object>> list = gson.fromJson(read(), List.class);
		for (Map<String, Object> map : list) {
			String table = (String) map.get("table");
			String topic = (String) map.get("topic");
			List<String> orders = (List<String>) map.get("orders");
			Map<String, Object> data = (Map<String, Object>) map.get("data");
			save(table, topic, orders, data);
		}
		ac.close();
	}

	@SuppressWarnings("unchecked")
	private static void save(String table, String topic, List<String> orders,
			Map<String, Object> data) {
		CTypeLogModel m = new CTypeLogModel();
		m.setName(table);
		simpleService.save(m);

		HbaseTableVersion tableVersion = putService.getHbaseTableVersion(table);
		CTypeLogModelVersion version = new CTypeLogModelVersion();
		version.setModel(m);
		version.setVersion("0.0");
		version.getRootNode().setHbaseTableVersion(tableVersion);
		simpleService.save(version);

		Map<String, HbaseTableColumn> nameToHbaseColumn = new HashMap<String, HbaseTableColumn>();
		for (HbaseTableColumn column : tableVersion.getColumns()) {
			nameToHbaseColumn.put(column.getName(), column);
		}

		for (String name : orders) {
			Object v = data.get(name);
			if (v.getClass().equals(String.class)) {
				CTypeLogModelSimpleColumn column = new CTypeLogModelSimpleColumn();
				column.setGroupColumn(version.getRootNode());
				column.setName(name);
				column.setHbaseTableColumn(nameToHbaseColumn.get(name));
				service.saveLogModelColumn(column);
			} else {
				Map<String, Object> map = (Map<String, Object>) v;
				String itemTable = (String) map.get("table");
				List<String> itemOrders = (List<String>) map.get("orders");

				HbaseTableVersion itemTableVersion = putService
						.getHbaseTableVersion(itemTable);

				CTypeLogModelGroupColumn column = new CTypeLogModelGroupColumn();
				column.setGroupColumn(version.getRootNode());
				column.setName(name);
				column.setHbaseTableVersion(itemTableVersion);
				service.saveLogModelColumn(column);

				Map<String, HbaseTableColumn> itemNameToHbaseColumn = new HashMap<String, HbaseTableColumn>();
				for (HbaseTableColumn hbaseColumn : itemTableVersion
						.getColumns()) {
					itemNameToHbaseColumn.put(hbaseColumn.getName(), hbaseColumn);
				}

				for (String itemName : itemOrders) {
					CTypeLogModelSimpleColumn itemColumn = new CTypeLogModelSimpleColumn();
					itemColumn.setGroupColumn(column);
					itemColumn.setName(itemName);
					itemColumn.setHbaseTableColumn(itemNameToHbaseColumn
							.get(itemName));
					service.saveLogModelColumn(itemColumn);
				}
			}
		}
		KafkaTopicFixedModelVersion kafkaTopic = new KafkaTopicFixedModelVersion();
		kafkaTopic.setName(topic);
		kafkaTopic.setCharset(KafkaTopicCharset.ISO88591);
//		kafkaTopic.setCharset(KafkaTopicCharset.UTF8);
		kafkaTopic.setIsEnabled(true);
		kafkaTopic.setVersion(version);
		simpleService.save(kafkaTopic);

	}

	public static String read() throws IOException {
		String path = CTypePut.class.getClassLoader().getResource("c.json")
				.getPath();
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		return new String(data, "UTF-8");
	}

	private static void init() {
		service = ac.getBean(CTypeLogModelMetaService.class);
		simpleService = ac.getBean(SimpleService.class);
		hbaseMetaService = ac.getBean(HbaseMetaService.class);
		putService = ac.getBean(CTypePutService.class);
	}
}
