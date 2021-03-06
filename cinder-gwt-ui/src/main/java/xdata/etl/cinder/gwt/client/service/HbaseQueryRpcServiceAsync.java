
package xdata.etl.cinder.gwt.client.service;

import java.util.Date;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import org.hibernate.validator.engine.ValidationSupport;
import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

public interface HbaseQueryRpcServiceAsync {


    public void dummy(AsyncCallback<ValidationSupport> callback);

    public void dummyShort(AsyncCallback<Short> callback);

    public void dummyDouble(AsyncCallback<Double> callback);

    public void dummyInteger(AsyncCallback<Integer> callback);

    public void dummyLong(AsyncCallback<Long> callback);

    public void dummyBoolean(AsyncCallback<Boolean> callback);

    public void dummyDate(AsyncCallback<Date> callback);

    public void dummyCharacter(AsyncCallback<Character> callback);

    public void get(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<HbaseRecord<String>>> callback);

}
