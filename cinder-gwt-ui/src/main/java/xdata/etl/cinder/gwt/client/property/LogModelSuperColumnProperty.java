
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelSuperColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public interface LogModelSuperColumnProperty
    extends PropertyAccess<LogModelSuperColumn>
{


    public ValueProvider<LogModelSuperColumn, LogModelColumnContent> content();

    public ValueProvider<LogModelSuperColumn, LogModelType> mtype();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelSuperColumn> key();

    public ValueProvider<LogModelSuperColumn, Integer> id();

    public ValueProvider<LogModelSuperColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<LogModelSuperColumn, Date> createTime();

    public ValueProvider<LogModelSuperColumn, String> createTimePropertyName();

}
