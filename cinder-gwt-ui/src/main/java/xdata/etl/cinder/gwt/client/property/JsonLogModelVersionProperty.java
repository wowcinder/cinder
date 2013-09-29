
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;

public interface JsonLogModelVersionProperty
    extends PropertyAccess<JsonLogModelVersion>
{


    public ValueProvider<JsonLogModelVersion, JsonLogModelGroupColumn> rootNode();

    public ValueProvider<JsonLogModelVersion, List<KafkaTopic>> topics();

    public ValueProvider<JsonLogModelVersion, String> desc();

    public ValueProvider<JsonLogModelVersion, String> version();

    public ValueProvider<JsonLogModelVersion, JsonLogModel> model();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<JsonLogModelVersion> key();

    public ValueProvider<JsonLogModelVersion, Integer> id();

    public ValueProvider<JsonLogModelVersion, Date> lastUpdateTimeStamp();

    public ValueProvider<JsonLogModelVersion, Date> createTime();

    public ValueProvider<JsonLogModelVersion, String> createTimePropertyName();

}
