
package xdata.etl.cinder.gwt.client.service;

import java.util.Date;
import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

public interface UserRpcServiceAsync {


    public void saveUser(User arg0, AsyncCallback<User> callback);

    public void updateUser(User arg0, AsyncCallback<User> callback);

    public void saveUserGroup(UserGroup arg0, AsyncCallback<UserGroup> callback);

    public void updateUserGroup(UserGroup arg0, AsyncCallback<UserGroup> callback);

    public void getUserGroupListForCombox(AsyncCallback<List<UserGroup>> callback);

    public void paging(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<User>> callback);

    public void dummyDate(AsyncCallback<Date> callback);

}
