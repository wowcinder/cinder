
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.LogModelColumnContentCType;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public interface LogModelColumnContentCTypeProperty
    extends PropertyAccess<LogModelColumnContentCType>
{


    public ValueProvider<LogModelColumnContentCType, Integer> pos();

    public ValueProvider<LogModelColumnContentCType, LogModelType> mtype();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelColumnContentCType> key();

    public ValueProvider<LogModelColumnContentCType, Integer> id();

    public ValueProvider<LogModelColumnContentCType, Date> lastUpdateTimeStamp();

    public ValueProvider<LogModelColumnContentCType, Date> createTime();

    public ValueProvider<LogModelColumnContentCType, String> createTimePropertyName();

}
