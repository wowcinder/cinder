
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public interface LogModelVersionProperty
    extends PropertyAccess<LogModelVersion>
{


    public ValueProvider<LogModelVersion, String> desc();

    public ValueProvider<LogModelVersion, String> version();

    public ValueProvider<LogModelVersion, LogModel> model();

    public ValueProvider<LogModelVersion, List<LogModelColumn>> columns();

    public ValueProvider<LogModelVersion, LogModelType> mtype();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelVersion> key();

    public ValueProvider<LogModelVersion, Integer> id();

    public ValueProvider<LogModelVersion, Date> lastUpdateTimeStamp();

    public ValueProvider<LogModelVersion, Date> createTime();

    public ValueProvider<LogModelVersion, String> createTimePropertyName();

}
