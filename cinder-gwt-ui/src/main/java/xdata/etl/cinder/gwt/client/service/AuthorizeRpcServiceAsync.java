
package xdata.etl.cinder.gwt.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import xdata.etl.cinder.shared.entity.authorize.Authorize;

public interface AuthorizeRpcServiceAsync {


    public void getAllocatenbeAuthorizes(AsyncCallback<List<Authorize>> callback);

}
