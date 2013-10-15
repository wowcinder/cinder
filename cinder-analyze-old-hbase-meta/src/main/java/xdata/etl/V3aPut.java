package xdata.etl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import xdata.etl.cinder.CTypePutService;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic.KafkaTopicCharset;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopicFixedModelVersion;
import xdata.etl.cinder.service.HbaseMetaService;
import xdata.etl.cinder.service.JsonLogModelMetaService;
import xdata.etl.cinder.service.SimpleService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class V3aPut {

	private static ClassPathXmlApplicationContext ac;
	public static JsonLogModelMetaService service;
	public static SimpleService simpleService;
	public static HbaseMetaService hbaseMetaService;
	private static CTypePutService putService;

	public static void main(String[] args) throws IOException {
		ac = new ClassPathXmlApplicationContext("spring-cinder-analyze.xml");
		init();
		GsonBuilder gb = new GsonBuilder();
		Gson gson = gb.create();
		List<Map<String, Object>> list = gson.fromJson(read("v3a.json"),
				List.class);
		for (Map<String, Object> map : list) {
			String table = (String) map.get("table");
			String topic = (String) map.get("topic");
			Map<String, Object> data = (Map<String, Object>) map.get("data");
			save(table, topic, data, KafkaTopicCharset.UTF8);
		}

		List<Map<String, Object>> list2 = gson.fromJson(read("assist.json"),
				List.class);
		for (Map<String, Object> map : list2) {
			String table = (String) map.get("table");
			String topic = (String) map.get("topic");
			Map<String, Object> data = (Map<String, Object>) map.get("data");
			save(table, topic, data, KafkaTopicCharset.GBK);
		}
		ac.close();
	}

	@SuppressWarnings("unchecked")
	private static void save(String table, String topic,
			Map<String, Object> data, KafkaTopicCharset charset) {
		JsonLogModel m = new JsonLogModel();
		m.setName(table);
		simpleService.save(m);

		HbaseTableVersion tableVersion = putService.getHbaseTableVersion(table);
		JsonLogModelVersion version = new JsonLogModelVersion();
		version.setModel(m);
		version.setVersion("0.0");
		version.getRootNode().setHbaseTableVersion(tableVersion);
		simpleService.save(version);

		Map<String, HbaseTableColumn> nameToHbaseColumn = new HashMap<String, HbaseTableColumn>();
		for (HbaseTableColumn column : tableVersion.getColumns()) {
			nameToHbaseColumn.put(column.getName(), column);
		}

		for (Entry<String, Object> entry : data.entrySet()) {
			String name = entry.getKey();
			Object v = entry.getValue();
			if (v.getClass().equals(String.class)) {
				String columnName = (String) v;
				JsonLogModelSimpleColumn column = new JsonLogModelSimpleColumn();
				column.setGroupColumn(version.getRootNode());
				column.setPath(name);
				column.setHbaseTableColumn(nameToHbaseColumn.get(columnName));
				service.saveLogModelColumn(column);
			} else {
				Map<String, Object> map = (Map<String, Object>) v;
				String itemTable = (String) map.get("table");

				HbaseTableVersion itemTableVersion = putService
						.getHbaseTableVersion(itemTable);

				JsonLogModelGroupColumn column = new JsonLogModelGroupColumn();
				column.setGroupColumn(version.getRootNode());
				column.setPath(name);
				column.setHbaseTableVersion(itemTableVersion);
				service.saveLogModelColumn(column);

				Map<String, HbaseTableColumn> itemNameToHbaseColumn = new HashMap<String, HbaseTableColumn>();
				for (HbaseTableColumn hbaseColumn : itemTableVersion
						.getColumns()) {
					itemNameToHbaseColumn.put(hbaseColumn.getName(),
							hbaseColumn);
				}

				for (Entry<String, Object> itemEntry : ((Map<String, Object>) map
						.get("data")).entrySet()) {
					String itemName = itemEntry.getKey();
					String itemColumnName = (String) itemEntry.getValue();
					JsonLogModelSimpleColumn itemColumn = new JsonLogModelSimpleColumn();
					itemColumn.setGroupColumn(column);
					itemColumn.setPath(itemName);
					itemColumn.setHbaseTableColumn(itemNameToHbaseColumn
							.get(itemColumnName));
					service.saveLogModelColumn(itemColumn);
				}
			}
		}
		KafkaTopicFixedModelVersion kafkaTopic = new KafkaTopicFixedModelVersion();
		kafkaTopic.setName(topic);
		kafkaTopic.setCharset(charset);
		kafkaTopic.setIsEnabled(true);
		kafkaTopic.setVersion(version);
		simpleService.save(kafkaTopic);

	}

	// public static String read(String fileName) throws IOException {
	// String path = CTypePut.class.getClassLoader().getResource(fileName)
	// .getPath();
	// InputStream fis = CTypePut.class.getClassLoader().getResourceAsStream(
	// fileName);
	// File file = new File(path);
	// FileInputStream fis = new FileInputStream(file);
	// byte[] data = new byte[(int) file.length()];
	// fis.read(data);
	// fis.close();
	// return new String(data, "UTF-8");
	// }
	public static String read(String fileName) throws IOException {
		InputStream fis = CTypePut.class.getClassLoader().getResourceAsStream(
				fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		return sb.toString();
	}

	private static void init() {
		service = ac.getBean(JsonLogModelMetaService.class);
		simpleService = ac.getBean(SimpleService.class);
		hbaseMetaService = ac.getBean(HbaseMetaService.class);
		putService = ac.getBean(CTypePutService.class);
	}
}
