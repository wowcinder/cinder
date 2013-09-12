/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelBase;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class LogModel extends LogModelBase {
	private static final long serialVersionUID = 6811992582287360379L;
	@NotNull
	@Length(min = 1, max = 50)
	@Column(nullable = true, unique = true, length = 50)
	private String name;

	@Column(name = "description", columnDefinition = "text")
	private String desc;

	@OneToMany(mappedBy = "model")
	private List<LogModelVersion> versions;

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public List<LogModelVersion> getVersions() {
		return versions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setVersions(List<LogModelVersion> versions) {
		this.versions = versions;
	}

}
