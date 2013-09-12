
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public interface LogModelColumnProperty
    extends PropertyAccess<LogModelColumn>
{


    public ValueProvider<LogModelColumn, LogModelVersion> version();

    public ValueProvider<LogModelColumn, LogModelColumnContent> content();

    public ValueProvider<LogModelColumn, LogModelType> mtype();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelColumn> key();

    public ValueProvider<LogModelColumn, Integer> id();

    public ValueProvider<LogModelColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<LogModelColumn, Date> createTime();

    public ValueProvider<LogModelColumn, String> createTimePropertyName();

}
