package xdata.etl.cinder.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.common.shared.entity.password.PasswordEncryptor;

@Service("SimplePasswordEncryptor")
public class SimplePasswordEncryptor implements PasswordEncryptor {

	@Override
	public String getEncryptPassword(String password) {
		if (password == null) {
			password = "";
		}
		return DigestUtils.md5Hex("xdata.etl.cinder" + password);
	}

}
