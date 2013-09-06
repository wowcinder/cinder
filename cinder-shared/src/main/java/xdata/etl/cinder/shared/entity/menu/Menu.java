/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.shared.entity.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.cinder.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Entity
@Table(name = "menu")
public class Menu extends MenuNode {
	private static final long serialVersionUID = -7971390347504825311L;

	private String token;

	private Authority requireAuthority;

	public Menu() {
	}

	@Column(length = 50, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 50)
	public String getToken() {
		return token;
	}

	@ManyToOne
	public Authority getRequireAuthority() {
		return requireAuthority;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setRequireAuthority(Authority requireAuthority) {
		this.requireAuthority = requireAuthority;
	}

}
