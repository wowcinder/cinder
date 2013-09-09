
package xdata.etl.cinder.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import xdata.etl.cinder.shared.entity.menu.MenuNode;

public interface AuthorizeRpcServiceAsync {


    public void logout(AsyncCallback<Void> callback);

    public void getUserMenus(AsyncCallback<List<MenuNode>> callback);

}
