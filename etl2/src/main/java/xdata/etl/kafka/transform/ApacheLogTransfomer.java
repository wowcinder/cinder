package xdata.etl.kafka.transform;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import xdata.etl.entity.apachelog.ApacheLog;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.transform.handler.KafkaTransformHandler;
import xdata.etl.kafka.transform.result.DefaultTransformResult;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

public class ApacheLogTransfomer implements KafkaTransformer {

	public ApacheLogTransfomer() {
	}

	@Override
	public KafkaTransformResult transform(Class<? extends HbaseEntity> clazz,
			String raw, KafkaTransformHandler... handlers)
			throws KafkaTransformException {
		String[] columns = raw.split("\\t");
		if (columns.length < 12) {
			throw new KafkaTransformException(clazz, raw,
					"column match exception");
		}
		ApacheLog log = new ApacheLog();
		for (int i = 0; i < columns.length; i++) {
			String v = columns[i].trim();
			try {
				fill(log, i, v);
			} catch (ParseException e) {
				throw new KafkaTransformException(clazz, raw, "parseException",
						e);
			} catch (RuntimeException e) {
				throw new KafkaTransformException(clazz, raw,
						"RuntimeException", e);
			}
		}
		log.setRaw(raw);

		KafkaTransformResult result = new DefaultTransformResult();
		result.add(log);
		for (KafkaTransformHandler handler : handlers) {
			handler.handler(result);
		}
		return result;
	}

	protected void fill(ApacheLog log, int index, String v)
			throws ParseException {
		if (index == 0) {
			log.setServerIp(v);
		}
		index--;
		switch (index) {
		case 0:
			log.setUserIp(v);
			break;
		case 1:
			log.setRequestTime(getValue(Date.class, v));
			break;
		case 2:
			log.setRequestMethod(v);
			break;
		case 3:
			log.setRequestUri(v);
			break;
		case 4:
			log.setUrlQueryString(v);
			break;
		case 5:
			log.setHttpCode(getValue(Integer.class, v));
			break;
		case 6:
			log.setResponseLen(getValue(Integer.class, v));
			break;
		case 7:
			log.setReferer(v);
			break;
		case 8:
			log.setUserAgent(v);
			break;
		case 9:
			log.setRequestHost(v);
			break;
		case 10:
			log.setUsedTime(getValue(Long.class, v));
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getValue(Class<T> clazz, String b)
			throws ParseException {
		if (b == null) {
			return null;
		}
		b = b.trim();
		if (!clazz.equals(String.class) && "".equals(b)) {
			return null;
		}
		if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
			return (T) new Boolean(b);
		} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
			return (T) new Long(b);
		} else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
			return (T) new Short(b);
		} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
			return (T) new Double(b);
		} else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
			return (T) new Float(b);
		} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return (T) new Integer(b);
		} else if (clazz.equals(BigDecimal.class)) {
			return (T) new BigDecimal(b);
		} else if (clazz.equals(Date.class)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return (T) df.parse(b);
		} else if (clazz.equals(BigInteger.class)) {
			return (T) new BigInteger(b);
		}
		return null;
	}

}
