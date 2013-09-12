/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.base.column;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Entity
public class LogModelPackageColumn extends LogModelColumn {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4240068788701345329L;
	@OneToMany(mappedBy = "parentColumn")
	private List<LogModelSubColumn> columns;

	public List<LogModelSubColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<LogModelSubColumn> columns) {
		this.columns = columns;
	}

}
