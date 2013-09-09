/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;

/**
 * @author XuehuiHe
 * @date 2013年8月22日
 */
@Entity
@Table(name = "menu_node")
@Inheritance(strategy = InheritanceType.JOINED)
public class MenuNode extends EntityHasTimeStampImpl implements Serializable {
	private static final long serialVersionUID = -692226783023465869L;

	private Integer id;
	private String name;
	private MenuGroup parent;
	private MenuNode prev;

	public MenuNode() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public MenuGroup getParent() {
		return parent;
	}

	@OneToOne
	public MenuNode getPrev() {
		return prev;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(MenuGroup parent) {
		this.parent = parent;
	}

	public void setPrev(MenuNode prev) {
		this.prev = prev;
	}

}
