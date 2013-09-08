
package xdata.etl.cinder.gwt.client.util;

import com.google.gwt.core.client.GWT;
import xdata.etl.cinder.gwt.client.service.AuthorizeRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.MenuRpcServiceAsync;

public class RpcServiceUtils {

    public final static AuthorizeRpcServiceAsync AuthorizeRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.AuthorizeRpcService.class);
    public final static MenuRpcServiceAsync MenuRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.MenuRpcService.class);

}
