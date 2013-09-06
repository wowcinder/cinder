/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.shared.entity.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.common.entity.timestamp.EntityHasTimeStampImpl;
import xdata.etl.cinder.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
@Entity
@Table(name = "user_group")
public class UserGroup extends EntityHasTimeStampImpl implements Serializable {
	private static final long serialVersionUID = 6538171186264110989L;
	private Integer id;
	private String name;
	private List<User> users;
	private List<Authority> authorities;

	public UserGroup() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Column(length = 20, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 20)
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "userGroup", cascade = CascadeType.REMOVE)
	public List<User> getUsers() {
		return users;
	}

	@ManyToMany
	@JoinTable(name = "user_group_to_authority")
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

}
