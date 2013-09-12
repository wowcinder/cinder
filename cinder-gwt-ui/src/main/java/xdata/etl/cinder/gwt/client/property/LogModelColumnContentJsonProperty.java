
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.LogModelColumnContentJson;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public interface LogModelColumnContentJsonProperty
    extends PropertyAccess<LogModelColumnContentJson>
{


    public ValueProvider<LogModelColumnContentJson, String> path();

    public ValueProvider<LogModelColumnContentJson, LogModelType> mtype();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelColumnContentJson> key();

    public ValueProvider<LogModelColumnContentJson, Integer> id();

    public ValueProvider<LogModelColumnContentJson, Date> lastUpdateTimeStamp();

    public ValueProvider<LogModelColumnContentJson, Date> createTime();

    public ValueProvider<LogModelColumnContentJson, String> createTimePropertyName();

}
