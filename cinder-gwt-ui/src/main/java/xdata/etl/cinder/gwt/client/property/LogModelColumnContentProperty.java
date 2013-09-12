
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public interface LogModelColumnContentProperty
    extends PropertyAccess<LogModelColumnContent>
{


    public ValueProvider<LogModelColumnContent, LogModelType> mtype();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelColumnContent> key();

    public ValueProvider<LogModelColumnContent, Integer> id();

    public ValueProvider<LogModelColumnContent, Date> lastUpdateTimeStamp();

    public ValueProvider<LogModelColumnContent, Date> createTime();

    public ValueProvider<LogModelColumnContent, String> createTimePropertyName();

}
