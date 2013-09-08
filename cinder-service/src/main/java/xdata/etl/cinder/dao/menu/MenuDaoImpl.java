package xdata.etl.cinder.dao.menu;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

@Repository
public class MenuDaoImpl implements MenuDao {
	@Resource(name = "cinderSf")
	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuNode> getUserMenus(Integer uid) {
		Session s = getSession();
		Criteria c = s.createCriteria(MenuNode.class);
		List<MenuNode> list = c.list();

		MenuListGenerator generator = new MenuListGenerator(list);
		if (uid == 0) {
			return generator.getRootNodes();
		} else {
			Set<Integer> aIds = new HashSet<Integer>();
			List<Authorize> authorities = s.createCriteria(Authorize.class)
					.createAlias("users", "user")
					.add(Restrictions.eq("user.id", uid)).list();
			List<Authorize> authorities2 = s.createCriteria(Authorize.class)
					.createAlias("userGroups", "ug")
					.createAlias("ug.users", "user")
					.add(Restrictions.eq("user.id", uid)).list();
			authorities.addAll(authorities2);
			for (Authorize authority : authorities) {
				aIds.add(authority.getId());
			}
			return generator.getUserNodes(aIds);
		}
	}

	Session getSession() {
		return sf.getCurrentSession();
	}
}
