
package xdata.etl.cinder.gwt.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import org.hibernate.validator.engine.ValidationSupport;
import xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

public interface KafkaRpcServiceAsync {


    public void dummy(AsyncCallback<ValidationSupport> callback);

    public void getLogModelVersions(AsyncCallback<List<LogModelVersion<?>>> callback);

    public void saveKafkaTopic(KafkaTopic arg0, AsyncCallback<KafkaTopic> callback);

    public void updateKafkaTopic(KafkaTopic arg0, AsyncCallback<KafkaTopic> callback);

    public void deleteKafkaTopics(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingKafkaTopic(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<KafkaTopic>> callback);

    public void saveKafkaWatchDog(KafkaWatchDog arg0, AsyncCallback<KafkaWatchDog> callback);

    public void updateKafkaWatchDog(KafkaWatchDog arg0, AsyncCallback<KafkaWatchDog> callback);

    public void deleteKafkaWatchDogs(List<Integer> arg0, AsyncCallback<Void> callback);

    public void pagingKafkaWatchDog(EtlPagingLoadConfigBean arg0, AsyncCallback<PagingLoadResult<KafkaWatchDog>> callback);

}
