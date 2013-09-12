/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelBase;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class LogModelVersion extends LogModelBase {
	private static final long serialVersionUID = -3007718172009171773L;
	@ManyToOne(optional = false)
	@JoinColumn
	private LogModel model;
	@Column(length = 40, nullable = false)
	@NotNull
	@Length(min = 1, max = 40)
	private String version;
	@Column(name = "description", columnDefinition = "text")
	private String desc;
	@OneToMany(mappedBy = "version", cascade = CascadeType.ALL)
	private List<LogModelColumn> columns;

	public LogModel getModel() {
		return model;
	}

	public String getVersion() {
		return version;
	}

	public String getDesc() {
		return desc;
	}

	public void setModel(LogModel model) {
		this.model = model;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<LogModelColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<LogModelColumn> columns) {
		this.columns = columns;
	}

}
