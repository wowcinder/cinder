package xdata.etl.cinder.logmodelmeta.shared.entity.json;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelBase;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "log_model_column_json", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"groupColumn_id", "path" }) })
public class JsonLogModelColumn extends LogModelBase {

	private static final long serialVersionUID = -9028881995182453040L;
	private String path;
	private JsonLogModelGroupColumn groupColumn;

	@ManyToOne
	public JsonLogModelGroupColumn getGroupColumn() {
		return groupColumn;
	}

	@Column(nullable = false, length = 150)
	@NotNull
	@Length(min = 1, max = 150)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setGroupColumn(JsonLogModelGroupColumn groupColumn) {
		this.groupColumn = groupColumn;
	}
}
