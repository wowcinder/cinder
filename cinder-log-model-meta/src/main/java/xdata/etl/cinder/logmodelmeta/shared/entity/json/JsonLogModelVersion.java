package xdata.etl.cinder.logmodelmeta.shared.entity.json;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelBase;

@Entity
@Table(name = "log_model_version_json", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"version", "model_id" }) })
public class JsonLogModelVersion extends LogModelBase {
	private static final long serialVersionUID = 2719663842833442034L;
	private String version;
	private String desc;
	private JsonLogModel model;
	private JsonLogModelGroupColumn rootNode;

	public JsonLogModelVersion(Integer vid, Integer rootId) {
		this();
		setId(vid);
		rootNode.setId(rootId);
	}

	public JsonLogModelVersion() {
		rootNode = new JsonLogModelGroupColumn();
		rootNode.setPos("root");
	}

	@Length(min = 1, max = 50)
	@NotNull
	@Column(nullable = false, length = 50)
	public String getVersion() {
		return version;
	}

	@ManyToOne(optional = false)
	@NotNull
	public JsonLogModel getModel() {
		return model;
	}

	@NotNull
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "root_node_id", nullable = false)
	public JsonLogModelGroupColumn getRootNode() {
		return rootNode;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setRootNode(JsonLogModelGroupColumn rootNode) {
		this.rootNode = rootNode;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setModel(JsonLogModel model) {
		this.model = model;
	}
}
