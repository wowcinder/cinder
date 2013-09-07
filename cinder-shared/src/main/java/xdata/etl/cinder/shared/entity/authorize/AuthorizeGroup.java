/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.shared.entity.authorize;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.common.entity.timestamp.EntityHasTimeStampImpl;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Table(name = "authorize_group")
@Entity
public class AuthorizeGroup extends EntityHasTimeStampImpl implements
		Serializable {

	private static final long serialVersionUID = -1351283572556439616L;

	private Integer id;
	private String name;
	private Integer displayOrder;
	private List<Authorize> authorizes;
	
	public AuthorizeGroup() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Column(length = 20, unique = true, nullable = false)
	@Length(min = 1, max = 20)
	@NotNull
	public String getName() {
		return name;
	}

	@Column(name = "display_order")
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	@OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
	public List<Authorize> getAuthorizes() {
		return authorizes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public void setAuthorizes(List<Authorize> authorizes) {
		this.authorizes = authorizes;
	}

}
