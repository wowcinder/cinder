package xdata.etl.cinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.dao.menu.MenuDao;
import xdata.etl.cinder.shared.annotations.MenuToken;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;

	@Override
	@Transactional(readOnly = true)
	public <T extends MenuNode> T save(MenuNode node) {
		return menuDao.save(node);
	}

	@Override
	@Transactional
	public <T extends MenuNode> T update(MenuNode node) {
		return menuDao.update(node);
	}

	@Override
	@Transactional
	public void deleteMenuNode(Integer id) {
		menuDao.deleteMenuNode(id);
	}

	@Override
	@Transactional
	public List<MenuNode> moveMenuNode(Integer parentId, Integer prevId,
			List<MenuNode> menuNodes) {
		return menuDao.moveMenuNode(parentId, prevId, menuNodes);
	}

	@Override
	@Transactional
	public void insertMenuConfig(List<MenuToken> tokens) {
		menuDao.insertMenuConfig(tokens);
	}

}
