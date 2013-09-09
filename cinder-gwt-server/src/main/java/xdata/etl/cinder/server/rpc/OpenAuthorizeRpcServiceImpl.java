package xdata.etl.cinder.server.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.gwt.client.service.OpenAuthorizeRpcService;
import xdata.etl.cinder.server.rpc.open.OpenRpcService;
import xdata.etl.cinder.service.AuthorizeService;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

@Service
public class OpenAuthorizeRpcServiceImpl implements OpenAuthorizeRpcService,
		OpenRpcService {
	@Autowired
	private AuthorizeService authorizeService;

	@Override
	public void logout() {
		authorizeService.logout();
	}

	@Override
	public List<MenuNode> getUserMenus() {
		return authorizeService.getUserMenus();
	}

}
