/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.ref;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xdata.etl.cinder.businessmeta.shared.BusinessColumnType.BusinessColumnTypeEnum;
import xdata.etl.cinder.businessmeta.shared.entity.BusinessToHbaseTableMapping;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@Entity
@Table(name = "business_column_sub_mapping_ref")
public class SubMappingBusinessColumnRef extends BusinessColumnRef {
	private static final long serialVersionUID = 192927475092201372L;
	private BusinessToHbaseTableMapping<?> subMapping;

	public SubMappingBusinessColumnRef() {
		setColumnType(BusinessColumnTypeEnum.SUB_MAPPING);
	}

	@ManyToOne
	@JoinColumn(name = "sub_mapping_id")
	public BusinessToHbaseTableMapping<?> getSubMapping() {
		return subMapping;
	}

	public void setSubMapping(BusinessToHbaseTableMapping<?> subMapping) {
		this.subMapping = subMapping;
	}

}
