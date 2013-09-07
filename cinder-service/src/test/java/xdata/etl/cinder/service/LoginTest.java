package xdata.etl.cinder.service;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.TestBase;
import xdata.etl.cinder.common.entity.password.PasswordEncryptor;
import xdata.etl.cinder.dao.user.UserDao;
import xdata.etl.cinder.shared.entity.user.User;

public class LoginTest extends TestBase {
	@Autowired
	private TestSaveUserService testService;

	@Test
	public void test() {
		String email = "kk2@Kk.com";
		String password = "kkk";
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);

		testService.saveUser(user);
		boolean result = testService.login(email, password);
		Assert.assertTrue(result);
		result = testService.login(email, password + "sdf");
		Assert.assertFalse(result);
	}

	@Service
	public static class TestSaveUserService {
		@Autowired
		private UserDao userDao;
		@Autowired
		private PasswordEncryptor passwordEncryptor;

		@Transactional
		public void saveUser(User user) {
			userDao.save(user);
		}

		@Transactional(readOnly = true)
		public boolean login(String email, String password) {
			return userDao.findUser(email,
					passwordEncryptor.getEncryptPassword(password)) != null;
		}
	}
}
