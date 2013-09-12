/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.base.column;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Entity
public class LogModelSubColumn extends LogModelSuperColumn {
	private static final long serialVersionUID = 7555432753357534760L;
	@ManyToOne
	private LogModelPackageColumn parentColumn;

	public LogModelPackageColumn getParentColumn() {
		return parentColumn;
	}

	public void setParentColumn(LogModelPackageColumn parentColumn) {
		this.parentColumn = parentColumn;
	}

}
