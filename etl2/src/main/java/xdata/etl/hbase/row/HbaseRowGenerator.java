package xdata.etl.hbase.row;

import xdata.etl.hbase.entity.HbaseEntity;

public interface HbaseRowGenerator {
	public String generate(HbaseEntity entity);
}
