package xdata.etl.kafka.transform.result;

import java.util.HashSet;
import java.util.Set;

import xdata.etl.hbase.entity.HbaseEntity;

public class DefaultTransformResult extends HashSet<HbaseEntity> implements
		KafkaTransformResult {
	private static final long serialVersionUID = -5554115422281835230L;

	public DefaultTransformResult() {
		super();
	}

	@Override
	public Set<HbaseEntity> get() {
		return this;
	}
}
