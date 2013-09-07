package xdata.etl.cinder.dao.authorize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.cinder.shared.entity.authorize.Authorize;
@Repository
public class AuthorizeDaoImpl implements AuthorizeDao {
	@Resource(name = "cinderSf")
	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getAuthorizeTokens(Integer uid) {
		Set<String> tokens = new HashSet<String>();

		List<Authorize> list = (List<Authorize>) getSession()
				.createCriteria(Authorize.class).createAlias("users", "user")
				.add(Restrictions.eq("user.id", uid)).list();
		List<Authorize> list2 = (List<Authorize>) getSession()
				.createCriteria(Authorize.class)
				.createAlias("userGroups", "userGroup")
				.createAlias("userGroup.users", "user")
				.add(Restrictions.eq("user.id", uid)).list();

		for (Authorize authorize : list) {
			tokens.add(authorize.getToken());
		}
		for (Authorize authorize : list2) {
			tokens.add(authorize.getToken());
		}
		return tokens;
	}

	Session getSession() {
		return sf.getCurrentSession();
	}

}
