package xdata.etl.cinder.server.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import xdata.etl.cinder.gwt.client.service.MenuRpcService;
import xdata.etl.cinder.server.CinderValidator;
import xdata.etl.cinder.service.MenuService;
import xdata.etl.cinder.shared.entity.menu.Menu;
import xdata.etl.cinder.shared.entity.menu.MenuGroup;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

@Service
@AuthorizeGroupAnnotation("菜单")
public class MenuRpcServiceImpl implements MenuRpcService {
	@Autowired
	private MenuService menuService;

	@Override
	@AuthorizeAnnotation("新建菜单组")
	public MenuGroup saveMenuGroup(MenuGroup menuGroup) {
		CinderValidator.validate(menuGroup);
		return menuService.save(menuGroup);
	}

	@Override
	@AuthorizeAnnotation("修改菜单组")
	public MenuGroup updateMenuGroup(MenuGroup menuGroup) {
		CinderValidator.validate(menuGroup);
		return menuService.update(menuGroup);
	}

	@Override
	@AuthorizeAnnotation("新建菜单")
	public Menu saveMenu(Menu menu) {
		CinderValidator.validate(menu);
		return menuService.save(menu);
	}

	@Override
	@AuthorizeAnnotation("修改菜单")
	public Menu updateMenu(Menu menu) {
		CinderValidator.validate(menu);
		return menuService.update(menu);
	}

	@Override
	@AuthorizeAnnotation("删除菜单(组)")
	public void deleteMenuNode(Integer id) {
		menuService.deleteMenuNode(id);
	}

	@Override
	@AuthorizeAnnotation("移动菜单(组)")
	public List<MenuNode> moveMenuNode(Integer parentId, Integer prevId,
			List<MenuNode> menuNodes) {
		return menuService.moveMenuNode(parentId, prevId, menuNodes);
	}

}
