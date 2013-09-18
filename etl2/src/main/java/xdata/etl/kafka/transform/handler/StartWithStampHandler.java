package xdata.etl.kafka.transform.handler;

import java.util.Date;
import java.util.Set;

import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.transform.result.KafkaTransformResult;

public class StartWithStampHandler implements KafkaTransformHandler {
	private Date stamp;

	public StartWithStampHandler(Date stamp) {
		this.stamp = stamp;
	}

	@Override
	public void handler(KafkaTransformResult result) {
		Set<HbaseEntity> sets = result.get();
		if (sets.size() > 0) {
			for (HbaseEntity entity : sets) {
				if (entity != null) {
					entity.setStamp(stamp);
				}
			}
		}
	}

	public Date getStamp() {
		return stamp;
	}

	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

}
