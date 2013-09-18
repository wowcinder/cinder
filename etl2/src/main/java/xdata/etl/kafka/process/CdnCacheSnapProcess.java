package xdata.etl.kafka.process;

import java.util.Set;

import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.exception.KafkaTopicProcessException;
import xdata.etl.kafka.exception.KafkaTransformException;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

public class CdnCacheSnapProcess extends StartWithStampProcess {
	@Override
	public void process(String raw) throws KafkaTopicProcessException,
			KafkaTransformException {
		beforePorcess(raw);
		KafkaTransformResult result = transform(raw);
		afterProcess(result);
		Set<HbaseEntity> entitys = result.get();
		if (entitys.size() == 0) {
			throw new KafkaTopicProcessException("cache snap path is empty");
		}
		// entitys.iterator().next();
		// TODO

	}
}
