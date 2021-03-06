package xdata.etl.kafka.process;

import java.util.Calendar;
import java.util.Date;

import xdata.etl.kafka.exception.KafkaTopicProcessException;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.transform.handler.KafkaTransformHandler;
import xdata.etl.kafka.transform.handler.StartWithStampHandler;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

public abstract class AbstractStartWithStampSnapProcess extends
		KafkaTopicProcess {

	public AbstractStartWithStampSnapProcess() {
	}

	protected void beforePorcess(String raw) throws KafkaTopicProcessException {
	}

	@Override
	public void process(String raw) throws KafkaTopicProcessException,
			KafkaTransformException {
		beforePorcess(raw);
		KafkaTransformResult result = transform(raw);
		afterProcess(result);
		deal(result);
	}

	public KafkaTransformResult transform(String raw)
			throws KafkaTopicProcessException, KafkaTransformException {
		int index = raw.indexOf('#');
		if (index == -1) {
			throw new KafkaTopicProcessException("Class:"
					+ getClazz().getName() + ",raw:" + raw + ",no found stamp!");
		}
		String time = raw.substring(0, index);
		String newraw = raw.substring(index + 1);
		Date stamp = getStamp(time);
		if (stamp == null) {
			throw new KafkaTopicProcessException("Class:"
					+ getClazz().getName() + ",raw:" + raw + ",stamp is wrong!");
		}
		KafkaTransformHandler handler = new StartWithStampHandler(stamp);
		return getTransformer().transform(getClazz(), newraw, handler);
	}

	protected abstract void deal(KafkaTransformResult result)
			throws KafkaTopicProcessException;

	protected void afterProcess(KafkaTransformResult result) {

	}

	protected Date getStamp(String stamp) {
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

}
