package xdata.etl.cinder.dao.user;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.cinder.shared.entity.user.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Resource(name = "cinderSf")
	private SessionFactory sf;

	Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public User findUser(String email, String encryptPassword) {
		return (User) getSession().createCriteria(User.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", encryptPassword))
				.uniqueResult();
	}

	@Override
	public void save(User user) {
		getSession().save(user);
	}

}
