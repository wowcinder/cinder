package xdata.etl.cinder.gwt.client.service;

import java.util.List;

import xdata.etl.cinder.shared.entity.menu.Menu;
import xdata.etl.cinder.shared.entity.menu.MenuGroup;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc/menu.rpc")
public interface MenuRpcService extends RemoteService {

	public MenuGroup saveMenuGroup(MenuGroup menuGroup);

	public MenuGroup updateMenuGroup(MenuGroup menuGroup);

	public Menu saveMenu(Menu menu);

	public Menu updateMenu(Menu menu);

	public void deleteMenuNode(Integer id);

	public List<MenuNode> moveMenuNode(Integer parentId, Integer prevId,
			List<MenuNode> menuNodes);

}
