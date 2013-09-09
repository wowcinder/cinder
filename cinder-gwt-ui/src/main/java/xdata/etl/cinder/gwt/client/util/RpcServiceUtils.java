
package xdata.etl.cinder.gwt.client.util;

import xdata.etl.cinder.gwt.client.service.AuthorizeRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.MenuRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.OpenAuthorizeRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.UserRpcServiceAsync;

import com.google.gwt.core.client.GWT;

public class RpcServiceUtils {

    public final static AuthorizeRpcServiceAsync AuthorizeRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.AuthorizeRpcService.class);
    public final static MenuRpcServiceAsync MenuRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.MenuRpcService.class);
    public final static OpenAuthorizeRpcServiceAsync OpenAuthorizeRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.OpenAuthorizeRpcService.class);
    public final static UserRpcServiceAsync UserRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.UserRpcService.class);

}
