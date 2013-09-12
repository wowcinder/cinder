
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelPackageColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelSubColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public class LogModelPackageColumnColumnConfig {


    public static ColumnConfig<LogModelPackageColumn, List<LogModelSubColumn>> columns() {
        ColumnConfig<LogModelPackageColumn, List<LogModelSubColumn>> columns = new ColumnConfig<LogModelPackageColumn, List<LogModelSubColumn>>(PropertyUtils.LogModelPackageColumnProperty.columns(), 200, "columns");
        columns.setSortable(false);
        columns.setMenuDisabled(true);
        return columns;
    }

    public static ColumnConfig<LogModelPackageColumn, LogModelVersion> version() {
        ColumnConfig<LogModelPackageColumn, LogModelVersion> version = new ColumnConfig<LogModelPackageColumn, LogModelVersion>(PropertyUtils.LogModelPackageColumnProperty.version(), 200, "version");
        version.setSortable(false);
        version.setMenuDisabled(true);
        return version;
    }

    public static ColumnConfig<LogModelPackageColumn, LogModelColumnContent> content() {
        ColumnConfig<LogModelPackageColumn, LogModelColumnContent> content = new ColumnConfig<LogModelPackageColumn, LogModelColumnContent>(PropertyUtils.LogModelPackageColumnProperty.content(), 200, "content");
        content.setSortable(false);
        content.setMenuDisabled(true);
        return content;
    }

    public static ColumnConfig<LogModelPackageColumn, LogModelType> mtype() {
        ColumnConfig<LogModelPackageColumn, LogModelType> mtype = new ColumnConfig<LogModelPackageColumn, LogModelType>(PropertyUtils.LogModelPackageColumnProperty.mtype(), 200, "mtype");
        mtype.setSortable(false);
        mtype.setMenuDisabled(true);
        return mtype;
    }

    public static ColumnConfig<LogModelPackageColumn, Integer> id() {
        ColumnConfig<LogModelPackageColumn, Integer> id = new ColumnConfig<LogModelPackageColumn, Integer>(PropertyUtils.LogModelPackageColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<LogModelPackageColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<LogModelPackageColumn, Date> lastUpdateTimeStamp = new ColumnConfig<LogModelPackageColumn, Date>(PropertyUtils.LogModelPackageColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<LogModelPackageColumn, Date> createTime() {
        ColumnConfig<LogModelPackageColumn, Date> createTime = new ColumnConfig<LogModelPackageColumn, Date>(PropertyUtils.LogModelPackageColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<LogModelPackageColumn, String> createTimePropertyName() {
        ColumnConfig<LogModelPackageColumn, String> createTimePropertyName = new ColumnConfig<LogModelPackageColumn, String>(PropertyUtils.LogModelPackageColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
