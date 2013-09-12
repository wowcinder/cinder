
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelPackageColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelSubColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public interface LogModelSubColumnProperty
    extends PropertyAccess<LogModelSubColumn>
{


    public ValueProvider<LogModelSubColumn, LogModelPackageColumn> parentColumn();

    public ValueProvider<LogModelSubColumn, LogModelColumnContent> content();

    public ValueProvider<LogModelSubColumn, LogModelType> mtype();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelSubColumn> key();

    public ValueProvider<LogModelSubColumn, Integer> id();

    public ValueProvider<LogModelSubColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<LogModelSubColumn, Date> createTime();

    public ValueProvider<LogModelSubColumn, String> createTimePropertyName();

}
