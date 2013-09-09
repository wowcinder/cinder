package xdata.etl.cinder.dao.user;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

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

	@Override
	public User saveUser(User user) {
		getSession().save(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		getSession().update(user);
		return user;
	}

	@Override
	public UserGroup saveUserGroup(UserGroup userGroup) {
		getSession().save(userGroup);
		return userGroup;
	}

	@Override
	public UserGroup updateUserGroup(UserGroup userGroup) {
		getSession().update(userGroup);
		return userGroup;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> getUserGroupListForCombox() {
		return getSession().createCriteria(UserGroup.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<User> paging(EtlPagingLoadConfigBean config)
			throws SharedException {
		PagingLoadResultBean<User> pr = new PagingLoadResultBean<User>();
		pr.setOffset(config.getOffset());

		Criteria criteria = getSession().createCriteria(User.class);

		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		pr.setTotalLength((int) rowCount);

		criteria.setProjection(null);
		criteria.setFirstResult(config.getOffset());
		criteria.setMaxResults(config.getLimit());
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

		pr.setData(criteria.list());

		return pr;
	}

}
