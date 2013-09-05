/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import xdata.etl.cinder.common.entity.EntityHasTimeStampImpl;

/**
 * @author XuehuiHe
 * @date 2013年9月5日
 */
@Entity
@Table(name = "test1")
public class Test1 extends EntityHasTimeStampImpl implements Serializable {
	private static final long serialVersionUID = -7764355967102025132L;
	private Integer id;

	public Test1() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
