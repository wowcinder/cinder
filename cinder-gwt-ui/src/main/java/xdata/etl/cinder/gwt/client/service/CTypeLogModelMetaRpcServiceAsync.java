
package xdata.etl.cinder.gwt.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import org.hibernate.validator.engine.ValidationSupport;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

public interface CTypeLogModelMetaRpcServiceAsync {


    public void saveLogModel(CTypeLogModel arg0, AsyncCallback<CTypeLogModel> callback);

    public void updateLogModel(CTypeLogModel arg0, AsyncCallback<CTypeLogModel> callback);

    public void deleteLogModels(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingLogModel(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<CTypeLogModel>> callback);

    public void getLogModels(AsyncCallback<List<CTypeLogModel>> callback);

    public void dummy(AsyncCallback<ValidationSupport> callback);

    public void saveLogModelVersion(CTypeLogModelVersion arg0, AsyncCallback<CTypeLogModelVersion> callback);

    public void updateLogModelVersion(CTypeLogModelVersion arg0, AsyncCallback<CTypeLogModelVersion> callback);

    public void deleteLogModelVersions(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingLogModelVersion(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<CTypeLogModelVersion>> callback);

    public void getLogModelVersions(AsyncCallback<List<CTypeLogModelVersion>> callback);

    public void getLogModelVersionRootNode(Integer arg0, AsyncCallback<CTypeLogModelGroupColumn> callback);

}
