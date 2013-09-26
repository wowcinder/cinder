package xdata.etl.cinder.logmodelmeta.shared.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;

@Entity
@Table(name = "log_model_version", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"version", "model_id" }) })
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LogModelVersion<RealLogModel extends LogModel<?>> extends
		LogModelBase {
	private static final long serialVersionUID = 2719663842833442034L;
	private String version;
	private String desc;
	private RealLogModel model;
	private List<KafkaTopic> topics;

	public LogModelVersion() {
	}

	@Length(min = 1, max = 50)
	@NotNull
	@Column(nullable = false, length = 50)
	public String getVersion() {
		return version;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	@ManyToOne(optional = false, targetEntity = LogModel.class)
	@NotNull
	public RealLogModel getModel() {
		return model;
	}

	@OneToMany(mappedBy = "version", cascade = CascadeType.REMOVE)
	public List<KafkaTopic> getTopics() {
		return topics;
	}

	public void setTopics(List<KafkaTopic> topics) {
		this.topics = topics;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setModel(RealLogModel model) {
		this.model = model;
	}
}
