/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.shared.entity.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.common.shared.entity.password.PasswordPersistence;
import xdata.etl.cinder.common.shared.entity.password.PasswordPersistenceImplHasTimeStamp;
import xdata.etl.cinder.shared.entity.authorize.Authorize;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
@Entity
@Table(name = "user")
public class User extends PasswordPersistenceImplHasTimeStamp implements
		Serializable, PasswordPersistence {
	private static final long serialVersionUID = -1801720788002068921L;

	private Integer id;
	private String email;
	private UserGroup userGroup;
	private List<Authorize> extraAuthorizes;

	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Column(length = 50, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 50)
	public String getEmail() {
		return email;
	}

	@ManyToOne
	@JoinColumn(name = "gid")
	public UserGroup getUserGroup() {
		return userGroup;
	}

	@ManyToMany
	@JoinTable(name = "user_to_extra_authorize", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = { @JoinColumn(name = "aid") })
	public List<Authorize> getExtraAuthorizes() {
		return extraAuthorizes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public void setExtraAuthorizes(List<Authorize> extraAuthorizes) {
		this.extraAuthorizes = extraAuthorizes;
	}

}
