package xdata.etl.cinder.gwt.client.service;

import java.util.List;

import xdata.etl.cinder.shared.entity.menu.MenuNode;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc/authorize.rpc")
public interface AuthorizeRpcService extends RemoteService {
	void logout();

	List<MenuNode> getUserMenus();
}
