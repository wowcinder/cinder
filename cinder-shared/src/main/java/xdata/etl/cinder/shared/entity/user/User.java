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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.common.entity.timestamp.EntityHasTimeStampImpl;
import xdata.etl.cinder.shared.entity.authorize.Authorize;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
@Entity
@Table(name = "user")
public class User extends EntityHasTimeStampImpl implements Serializable {
	private static final long serialVersionUID = -1801720788002068921L;

	private Integer id;
	private String email;
	private String password;
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

	@Column(length = 32)
	@NotNull
	@Length(min = 32, max = 32)
	public String getPassword() {
		return password;
	}

	@ManyToOne
	public UserGroup getUserGroup() {
		return userGroup;
	}

	@ManyToMany
	@JoinTable(name = "user_to_extra_authorize")
	public List<Authorize> getExtraAuthorizes() {
		return extraAuthorizes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public void setExtraAuthorizes(List<Authorize> extraAuthorizes) {
		this.extraAuthorizes = extraAuthorizes;
	}

}
