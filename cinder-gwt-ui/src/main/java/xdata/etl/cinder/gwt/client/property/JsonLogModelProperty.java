
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;

public interface JsonLogModelProperty
    extends PropertyAccess<JsonLogModel>
{


    public ValueProvider<JsonLogModel, List<JsonLogModelVersion>> versions();

    public ValueProvider<JsonLogModel, String> name();

    public ValueProvider<JsonLogModel, String> desc();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<JsonLogModel> key();

    public ValueProvider<JsonLogModel, Integer> id();

    public ValueProvider<JsonLogModel, Date> lastUpdateTimeStamp();

    public ValueProvider<JsonLogModel, Date> createTime();

    public ValueProvider<JsonLogModel, String> createTimePropertyName();

}
