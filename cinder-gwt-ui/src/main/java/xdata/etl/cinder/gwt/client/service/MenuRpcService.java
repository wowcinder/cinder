package xdata.etl.cinder.gwt.client.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import xdata.etl.cinder.shared.entity.menu.Menu;
import xdata.etl.cinder.shared.entity.menu.MenuGroup;
import xdata.etl.cinder.shared.entity.menu.MenuNode;
import xdata.etl.cinder.shared.exception.SharedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc/menu.rpc")
public interface MenuRpcService extends RemoteService {

	public MenuGroup saveMenuGroup(MenuGroup menuGroup) throws SharedException,
			ConstraintViolationException;

	public MenuGroup updateMenuGroup(MenuGroup menuGroup)
			throws SharedException, ConstraintViolationException;

	public Menu saveMenu(Menu menu) throws SharedException,
			ConstraintViolationException;

	public Menu updateMenu(Menu menu) throws SharedException,
			ConstraintViolationException;

	public void deleteMenuNode(Integer id) throws SharedException,
			ConstraintViolationException;

	public List<MenuNode> moveMenuNode(Integer parentId, Integer prevId,
			List<MenuNode> menuNodes) throws SharedException,
			ConstraintViolationException;

}
