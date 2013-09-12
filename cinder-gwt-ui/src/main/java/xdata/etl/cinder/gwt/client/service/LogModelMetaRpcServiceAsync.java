
package xdata.etl.cinder.gwt.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import org.hibernate.validator.engine.ValidationSupport;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

public interface LogModelMetaRpcServiceAsync {


    public void dummy(AsyncCallback<ValidationSupport> callback);

    public void saveLogModel(LogModel arg0, AsyncCallback<LogModel> callback);

    public void updateLogModel(LogModel arg0, AsyncCallback<LogModel> callback);

    public void deleteLogModels(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingLogModel(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<LogModel>> callback);

    public void getLogModels(AsyncCallback<List<LogModel>> callback);

    public void saveVersion(LogModelVersion arg0, AsyncCallback<LogModelVersion> callback);

}
