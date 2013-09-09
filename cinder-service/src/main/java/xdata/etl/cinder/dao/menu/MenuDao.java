package xdata.etl.cinder.dao.menu;

import java.util.List;

import xdata.etl.cinder.shared.annotations.MenuToken;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

public interface MenuDao {

	List<MenuNode> getUserMenus(Integer uid);

	<T extends MenuNode> T save(MenuNode node);

	<T extends MenuNode> T update(MenuNode node);

	void deleteMenuNode(Integer id);

	List<MenuNode> moveMenuNode(Integer parentId, Integer prevId,
			List<MenuNode> menuNodes);

	void insertMenuConfig(List<MenuToken> tokens);

}
