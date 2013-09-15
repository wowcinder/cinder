package xdata.etl.cinder.logmodelmeta.shared.entity.c;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;

@Entity
public class CTypeLogModelSimpleColumn extends CTypeLogModelColumn {

	private static final long serialVersionUID = -5597434068603255025L;
	private HbaseTableColumn hbaseTableColumn;

	@OneToOne
	@JoinColumn(name = "hbaseTableColumn_id")
	public HbaseTableColumn getHbaseTableColumn() {
		return hbaseTableColumn;
	}

	public void setHbaseTableColumn(HbaseTableColumn hbaseTableColumn) {
		this.hbaseTableColumn = hbaseTableColumn;
	}

}
