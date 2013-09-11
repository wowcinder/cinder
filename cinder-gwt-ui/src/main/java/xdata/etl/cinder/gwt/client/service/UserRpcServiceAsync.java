
package xdata.etl.cinder.gwt.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import org.hibernate.validator.engine.ValidationSupport;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

public interface UserRpcServiceAsync {


    public void dummy(AsyncCallback<ValidationSupport> callback);

    public void saveUser(User arg0, String arg1, AsyncCallback<User> callback);

    public void updateUser(User arg0, String arg1, AsyncCallback<User> callback);

    public void saveUserGroup(UserGroup arg0, AsyncCallback<UserGroup> callback);

    public void updateUserGroup(UserGroup arg0, AsyncCallback<UserGroup> callback);

    public void getUserGroupListForCombox(AsyncCallback<List<UserGroup>> callback);

    public void deleteUsers(List<Integer> arg0, AsyncCallback<Void> callback);

    public void deleteUserGroups(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingUser(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<User>> callback);

    public void pagingUserGroup(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<UserGroup>> callback);

    public void getUserExtraAuthorizes(Integer arg0, AsyncCallback<List<Authorize>> callback);

    public void getUserGroupAuthorizes(Integer arg0, AsyncCallback<List<Authorize>> callback);

}
