/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.common.entity.password;

/**
 * @author XuehuiHe
 * @date 2013年9月6日
 */
public interface PasswordEncryptor {
	String getEncryptPassword(String password);
}
