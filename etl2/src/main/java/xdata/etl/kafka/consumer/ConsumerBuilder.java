package xdata.etl.kafka.consumer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.Resource;

import xdata.etl.hbase.HbaseContext;
import xdata.etl.kafka.KafkaContext;

public class ConsumerBuilder {
	private HbaseContext hbaseCxt;
	private KafkaContext kafkaCxt;
	private Integer threadCount = 1;

	private Map<String, Integer> topicCountMap;
	private Resource topicFile;
	private Resource snapTopicFile;

	public ConsumerBuilder() {
		topicCountMap = new HashMap<String, Integer>();
	}

	public IConsumer create() {
		dealConfigFile();
		SimpleConsumer c = new SimpleConsumer(getTopicCountMap());
		c.setHbaseCxt(getHbaseCxt());
		c.setKafkaCxt(getKafkaCxt());
		return c;
	}

	private void dealConfigFile() {
		if (getTopicFile().exists()) {
			dealConfigFile(getTopicFile(), false);
		}
		if (getSnapTopicFile().exists()) {
			dealConfigFile(getSnapTopicFile(), true);
		}
	}

	protected void dealConfigFile(Resource resource, boolean isSnap) {
		try {
			File file = resource.getFile();
			AnalyzeTopicConfFileResult map = analyzeTopicConfFile(file);
			if (map.isAll()) {
				Set<String> topics = null;
				if (isSnap) {
					topics = getKafkaCxt().getTopicManager().getSnapTopic();
				} else {
					topics = getKafkaCxt().getTopicManager().getTopics();
				}
				for (String topic : topics) {
					getTopicCountMap().put(topic, map.getThreadCount());
				}
			} else {
				getTopicCountMap().putAll(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected AnalyzeTopicConfFileResult analyzeTopicConfFile(File file) {
		AnalyzeTopicConfFileResult map = new AnalyzeTopicConfFileResult();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.equals("all") || line.startsWith("all:")) {
					analyzeTopicConfLine(line, map);
					map.setAll(true);
					map.setThreadCount(map.get("all"));
					break;
				} else {
					analyzeTopicConfLine(line, map);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	protected void analyzeTopicConfLine(String line, Map<String, Integer> map) {
		int index = line.indexOf(":");
		if (index == -1) {
			map.put(line, getThreadCount());
			return;
		}
		String topic = line.substring(0, index);
		Integer threadNum = Integer.parseInt(line.substring(index + 1));
		map.put(topic, threadNum);
	}

	public HbaseContext getHbaseCxt() {
		return hbaseCxt;
	}

	public KafkaContext getKafkaCxt() {
		return kafkaCxt;
	}

	public Integer getThreadCount() {
		return threadCount;
	}

	public void setHbaseCxt(HbaseContext hbaseCxt) {
		this.hbaseCxt = hbaseCxt;
	}

	public void setKafkaCxt(KafkaContext kafkaCxt) {
		this.kafkaCxt = kafkaCxt;
	}

	public void setThreadCount(Integer threadCount) {
		this.threadCount = threadCount;
	}

	public void setTopicFile(Resource topicFile) {
		this.topicFile = topicFile;
	}

	public void setSnapTopicFile(Resource snapTopicFile) {
		this.snapTopicFile = snapTopicFile;
	}

	public Resource getTopicFile() {
		return topicFile;
	}

	public Resource getSnapTopicFile() {
		return snapTopicFile;
	}

	public static class AnalyzeTopicConfFileResult extends
			HashMap<String, Integer> {
		private static final long serialVersionUID = 934628372613082531L;

		private boolean isAll = false;
		private int threadCount = 1;

		public AnalyzeTopicConfFileResult() {
		}

		public boolean isAll() {
			return isAll;
		}

		public void setAll(boolean isAll) {
			this.isAll = isAll;
		}

		public int getThreadCount() {
			return threadCount;
		}

		public void setThreadCount(int threadCount) {
			this.threadCount = threadCount;
		}

	}

	public Map<String, Integer> getTopicCountMap() {
		return topicCountMap;
	}

	public void setTopicCountMap(Map<String, Integer> topicCountMap) {
		this.topicCountMap = topicCountMap;
	}
}
