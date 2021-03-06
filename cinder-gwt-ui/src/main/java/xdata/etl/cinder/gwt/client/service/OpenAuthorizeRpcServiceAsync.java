
package xdata.etl.cinder.gwt.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

public interface OpenAuthorizeRpcServiceAsync {


    public void logout(AsyncCallback<Void> callback);

    public void getUserMenus(AsyncCallback<List<MenuNode>> callback);

    public void login(String arg0, String arg1, AsyncCallback<Boolean> callback);

    public void isLogin(AsyncCallback<Boolean> callback);

}
