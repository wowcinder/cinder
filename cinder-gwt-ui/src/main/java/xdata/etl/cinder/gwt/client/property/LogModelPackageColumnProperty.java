
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelPackageColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelSubColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public interface LogModelPackageColumnProperty
    extends PropertyAccess<LogModelPackageColumn>
{


    public ValueProvider<LogModelPackageColumn, List<LogModelSubColumn>> columns();

    public ValueProvider<LogModelPackageColumn, LogModelVersion> version();

    public ValueProvider<LogModelPackageColumn, LogModelColumnContent> content();

    public ValueProvider<LogModelPackageColumn, LogModelType> mtype();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelPackageColumn> key();

    public ValueProvider<LogModelPackageColumn, Integer> id();

    public ValueProvider<LogModelPackageColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<LogModelPackageColumn, Date> createTime();

    public ValueProvider<LogModelPackageColumn, String> createTimePropertyName();

}
