
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public interface LogModelProperty
    extends PropertyAccess<LogModel>
{


    public ValueProvider<LogModel, List<LogModelVersion>> versions();

    public ValueProvider<LogModel, String> name();

    public ValueProvider<LogModel, String> desc();

    public ValueProvider<LogModel, LogModelType> mtype();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModel> key();

    public ValueProvider<LogModel, Integer> id();

    public ValueProvider<LogModel, Date> lastUpdateTimeStamp();

    public ValueProvider<LogModel, Date> createTime();

    public ValueProvider<LogModel, String> createTimePropertyName();

}
