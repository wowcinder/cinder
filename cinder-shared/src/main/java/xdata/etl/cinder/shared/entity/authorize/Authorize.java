/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.shared.entity.authorize;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;
import xdata.etl.cinder.shared.entity.menu.Menu;
import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Entity
@Table(name = "authorize")
public class Authorize extends EntityHasTimeStampImpl implements Serializable {
	private static final long serialVersionUID = -7252452619145327784L;

	private Integer id;
	private String token;
	private String name;
	private Integer displayOrder;
	private AuthorizeGroup group;
	private Set<Menu> menus;
	private Set<User> users;
	private Set<UserGroup> userGroups;

	public Authorize() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Column(unique = true, nullable = false, length = 32)
	@NotNull
	@Length(min = 32, max = 32)
	public String getToken() {
		return token;
	}

	@Column(length = 30, nullable = false)
	@Length(min = 1, max = 30)
	@NotNull
	public String getName() {
		return name;
	}

	@Column(name = "display_order")
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	@ManyToOne
	@JoinColumn(name = "gid")
	public AuthorizeGroup getGroup() {
		return group;
	}

	@OneToMany(mappedBy = "requireAuthorize")
	public Set<Menu> getMenus() {
		return menus;
	}

	@ManyToMany(mappedBy = "extraAuthorizes")
	public Set<User> getUsers() {
		return users;
	}

	@ManyToMany(mappedBy = "authorizes")
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public void setGroup(AuthorizeGroup group) {
		this.group = group;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

}
