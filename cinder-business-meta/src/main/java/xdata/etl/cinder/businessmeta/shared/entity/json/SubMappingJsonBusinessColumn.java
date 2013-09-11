/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.businessmeta.shared.entity.json;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import xdata.etl.cinder.businessmeta.shared.BusinessType.BusinessColumnType;
import xdata.etl.cinder.businessmeta.shared.entity.SubMappingBusinessColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_column_json_sub_mapping")
public class SubMappingJsonBusinessColumn extends JsonBusinessColumn implements
		SubMappingBusinessColumn<JsonBusinessToHbaseTableMapping> {
	private static final long serialVersionUID = -1420027564993173337L;
	@ManyToOne
	@JoinColumn(name = "sub_mapping_id")
	private JsonBusinessToHbaseTableMapping subMapping;

	public JsonBusinessToHbaseTableMapping getSubMapping() {
		return subMapping;
	}

	public void setSubMapping(JsonBusinessToHbaseTableMapping subMapping) {
		this.subMapping = subMapping;
	}

	@Override
	public BusinessColumnType getColumnType() {
		return BusinessColumnType.SUB_MAPPING;
	}
}
