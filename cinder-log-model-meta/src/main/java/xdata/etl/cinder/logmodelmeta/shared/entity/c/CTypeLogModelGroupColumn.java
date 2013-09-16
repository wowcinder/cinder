package xdata.etl.cinder.logmodelmeta.shared.entity.c;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;

@Entity
public class CTypeLogModelGroupColumn extends CTypeLogModelColumn {
	private static final long serialVersionUID = 1424230533361555956L;
	private List<CTypeLogModelColumn> columns;
	private HbaseTableVersion hbaseTableVersion;

	@OneToMany(mappedBy = "groupColumn")
	public List<CTypeLogModelColumn> getColumns() {
		return columns;
	}

	@OneToOne
	@JoinColumn(name = "hbaseTableVersion_id", nullable = false)
	@NotNull
	public HbaseTableVersion getHbaseTableVersion() {
		return hbaseTableVersion;
	}

	public void setHbaseTableVersion(HbaseTableVersion hbaseTableVersion) {
		this.hbaseTableVersion = hbaseTableVersion;
	}

	public void setColumns(List<CTypeLogModelColumn> columns) {
		this.columns = columns;
	}

}