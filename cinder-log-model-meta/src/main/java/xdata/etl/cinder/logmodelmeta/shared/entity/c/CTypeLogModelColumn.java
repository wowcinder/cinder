package xdata.etl.cinder.logmodelmeta.shared.entity.c;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.common.shared.groups.RpcChecks;
import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelBase;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {
		"groupColumn_id", "pos" }) })
public class CTypeLogModelColumn extends LogModelBase {

	private static final long serialVersionUID = -9028881995182453040L;
	private Integer pos;
	private String name;
	private CTypeLogModelGroupColumn groupColumn;

	@ManyToOne
	public CTypeLogModelGroupColumn getGroupColumn() {
		return groupColumn;
	}

	@NotNull(groups = RpcChecks.class)
	@Column(nullable = false)
	public Integer getPos() {
		return pos;
	}

	@NotNull
	@Column(nullable = false, length = 40)
	@Length(min = 1, max = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setGroupColumn(CTypeLogModelGroupColumn groupColumn) {
		this.groupColumn = groupColumn;
	}
}
