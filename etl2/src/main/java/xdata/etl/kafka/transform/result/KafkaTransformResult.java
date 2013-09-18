package xdata.etl.kafka.transform.result;

import java.util.Collection;
import java.util.Set;

import xdata.etl.hbase.entity.HbaseEntity;

public interface KafkaTransformResult {
	Set<HbaseEntity> get();

	boolean add(HbaseEntity entity);

	boolean addAll(Collection<? extends HbaseEntity> entitys);

}
