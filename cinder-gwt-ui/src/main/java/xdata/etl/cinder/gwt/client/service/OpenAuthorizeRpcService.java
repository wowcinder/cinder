package xdata.etl.cinder.gwt.client.service;

import java.util.List;

import xdata.etl.cinder.shared.entity.menu.MenuNode;
import xdata.etl.cinder.shared.exception.SharedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc/open_authorize.rpc")
public interface OpenAuthorizeRpcService extends RemoteService {
	void logout();

	List<MenuNode> getUserMenus() throws SharedException;

	Boolean login(String email, String password);

	Boolean isLogin();
}
