package xdata.etl.cinder.dao.menu;

import java.util.List;

import xdata.etl.cinder.shared.entity.menu.MenuNode;

public interface MenuDao {

	List<MenuNode> getUserMenus(Integer uid);

}
