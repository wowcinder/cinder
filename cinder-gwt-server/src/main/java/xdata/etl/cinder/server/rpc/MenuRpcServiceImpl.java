package xdata.etl.cinder.server.rpc;

import java.util.List;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import xdata.etl.cinder.gwt.client.service.MenuRpcService;
import xdata.etl.cinder.server.AuthorizeNames.AuthorizeAnnotationNamesForMenu;
import xdata.etl.cinder.service.MenuService;
import xdata.etl.cinder.shared.entity.menu.Menu;
import xdata.etl.cinder.shared.entity.menu.MenuGroup;
import xdata.etl.cinder.shared.entity.menu.MenuNode;
import xdata.etl.cinder.util.CinderValidator;

@Service
@AuthorizeGroupAnnotation(AuthorizeAnnotationNamesForMenu.GROUP)
public class MenuRpcServiceImpl implements MenuRpcService {
	@Autowired
	private MenuService menuService;

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public MenuGroup saveMenuGroup(MenuGroup menuGroup) {
		CinderValidator.validate(menuGroup);
		return menuService.save(menuGroup);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public MenuGroup updateMenuGroup(MenuGroup menuGroup) {
		CinderValidator.validate(menuGroup);
		return menuService.update(menuGroup);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public Menu saveMenu(Menu menu) {
		CinderValidator.validate(menu);
		return menuService.save(menu);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public Menu updateMenu(Menu menu) {
		CinderValidator.validate(menu);
		return menuService.update(menu);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public void deleteMenuNode(Integer id) {
		menuService.deleteMenuNode(id);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForMenu.ALL)
	public List<MenuNode> moveMenuNode(Integer parentId, Integer prevId,
			List<MenuNode> menuNodes) {
		return menuService.moveMenuNode(parentId, prevId, menuNodes);
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

}
