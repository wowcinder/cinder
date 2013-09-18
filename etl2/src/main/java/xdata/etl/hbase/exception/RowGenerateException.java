package xdata.etl.hbase.exception;

import xdata.etl.hbase.entity.HbaseEntity;

public class RowGenerateException extends HbaseORMException {
	private static final long serialVersionUID = -9194643408747066152L;
	private HbaseEntity entity;

	public RowGenerateException(HbaseEntity entity) {
		this.entity = entity;
	}

	public HbaseEntity getEntity() {
		return entity;
	}

	public void setEntity(HbaseEntity entity) {
		this.entity = entity;
	}

}
