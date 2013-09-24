package xdata.etl.cinder.logmodelmeta.shared.entity.json;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelBase;

@Entity
@Table(name = "log_model_json")
public class JsonLogModel extends LogModelBase {
	private static final long serialVersionUID = 5087339932449259266L;
	private String name;
	private String desc;
	private List<JsonLogModelVersion> versions;

	@NotNull
	@Length(min = 1, max = 100)
	@Column(nullable = false, unique = true, length = 100)
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "model")
	public List<JsonLogModelVersion> getVersions() {
		return versions;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersions(List<JsonLogModelVersion> versions) {
		this.versions = versions;
	}

}
