
package xdata.etl.cinder.gwt.client.util;

import com.google.gwt.core.client.GWT;
import xdata.etl.cinder.gwt.client.service.AuthorizeRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.CTypeLogModelMetaRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.HbaseMetaRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.HbaseQueryRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.JsonLogModelMetaRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.MenuRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.OpenAuthorizeRpcServiceAsync;
import xdata.etl.cinder.gwt.client.service.UserRpcServiceAsync;

public class RpcServiceUtils {

    public final static AuthorizeRpcServiceAsync AuthorizeRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.AuthorizeRpcService.class);
    public final static CTypeLogModelMetaRpcServiceAsync CTypeLogModelMetaRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.CTypeLogModelMetaRpcService.class);
    public final static HbaseMetaRpcServiceAsync HbaseMetaRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.HbaseMetaRpcService.class);
    public final static HbaseQueryRpcServiceAsync HbaseQueryRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.HbaseQueryRpcService.class);
    public final static JsonLogModelMetaRpcServiceAsync JsonLogModelMetaRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.JsonLogModelMetaRpcService.class);
    public final static MenuRpcServiceAsync MenuRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.MenuRpcService.class);
    public final static OpenAuthorizeRpcServiceAsync OpenAuthorizeRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.OpenAuthorizeRpcService.class);
    public final static UserRpcServiceAsync UserRpcService = GWT.create(xdata.etl.cinder.gwt.client.service.UserRpcService.class);

}
