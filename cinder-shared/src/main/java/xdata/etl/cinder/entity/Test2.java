/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import xdata.etl.cinder.common.entity.timestamp.EntityHasTimeStampImpl;

@Entity
@Table(name = "test2")
public class Test2 extends EntityHasTimeStampImpl implements Serializable {
	private static final long serialVersionUID = -7764355967102025132L;
	private Integer id;
	private String name;

	public Test2() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
