/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.dao.user;

import xdata.etl.cinder.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年9月6日
 */
public interface UserDao {
	User findUser(String email, String encryptPassword);

	void save(User user);
}
