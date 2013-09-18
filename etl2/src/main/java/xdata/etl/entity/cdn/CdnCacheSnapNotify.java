package xdata.etl.entity.cdn;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xdata.etl.entity.cdn.CdnCacheSnapNotify.CdnCacheSnapNotifyTransformer;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.annotatins.Kafka;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.process.CdnSnapProcess;
import xdata.etl.kafka.transform.KafkaTransformer;
import xdata.etl.kafka.transform.handler.KafkaTransformHandler;
import xdata.etl.kafka.transform.result.DefaultTransformResult;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

@Kafka(topic = "cdn_cache_snap_notify", transformer = CdnCacheSnapNotifyTransformer.class, process = CdnSnapProcess.class, isSnap = true)
public class CdnCacheSnapNotify extends HbaseEntity implements Serializable {
	private static final long serialVersionUID = -1302363308286328173L;

	private String ip;
	private String dayStr;
	private String fileName;

	public CdnCacheSnapNotify() {
	}

	public String getIp() {
		return ip;
	}

	public String getDayStr() {
		return dayStr;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public static class CdnCacheSnapNotifyTransformer implements
			KafkaTransformer {
		@Override
		public KafkaTransformResult transform(
				Class<? extends HbaseEntity> clazz, String raw,
				KafkaTransformHandler... handlers)
				throws KafkaTransformException {
			raw.matches("^vistrate\\_\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\_\\d{8}\\.dmp$");
			String regex = "^vistrate\\_(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\_(\\d{8})\\.dmp$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(raw);
			if (!m.matches()) {
				throw new KafkaTransformException(CdnCacheSnapNotify.class,
						raw, "fileName is illegal");
			}
			CdnCacheSnapNotify notify = new CdnCacheSnapNotify();
			notify.setFileName(raw);
			notify.setDayStr(m.group(2));
			notify.setIp(m.group(1));

			KafkaTransformResult result = new DefaultTransformResult();
			result.add(notify);
			return result;
		}

	}

}
