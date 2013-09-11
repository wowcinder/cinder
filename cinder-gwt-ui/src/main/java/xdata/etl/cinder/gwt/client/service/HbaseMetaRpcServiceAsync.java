
package xdata.etl.cinder.gwt.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

public interface HbaseMetaRpcServiceAsync {


    public void saveHbaseTable(HbaseTable arg0, AsyncCallback<HbaseTable> callback);

    public void deleteHbaseTable(Integer arg0, AsyncCallback<Void> callback);

    public void updateHbaseTable(HbaseTable arg0, AsyncCallback<HbaseTable> callback);

    public void pagingHbaseTable(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<HbaseTable>> callback);

    public void saveHbaseTableVersion(HbaseTableVersion arg0, AsyncCallback<HbaseTableVersion> callback);

    public void deleteHbaseTableVersion(Integer arg0, AsyncCallback<Void> callback);

    public void updateHbaseTableVersion(HbaseTableVersion arg0, AsyncCallback<HbaseTableVersion> callback);

    public void pagingHbaseTableVersion(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<HbaseTableVersion>> callback);

    public void saveHbaseTableColumn(HbaseTableColumn arg0, AsyncCallback<HbaseTableColumn> callback);

    public void deleteHbaseTableColumn(Integer arg0, AsyncCallback<Void> callback);

    public void updateHbaseTableColumn(HbaseTableColumn arg0, AsyncCallback<HbaseTableColumn> callback);

    public void pagingHbaseTableColumn(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<HbaseTableColumn>> callback);

    public void deleteHbaseTables(List<Integer> arg0, AsyncCallback<Void> callback);

    public void deleteHbaseTableVersions(List<Integer> arg0, AsyncCallback<Void> callback);

    public void deleteHbaseTableColumns(List<Integer> arg0, AsyncCallback<Void> callback);

}
