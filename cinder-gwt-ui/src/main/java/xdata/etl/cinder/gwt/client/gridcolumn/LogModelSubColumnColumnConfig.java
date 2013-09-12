
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelPackageColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelSubColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public class LogModelSubColumnColumnConfig {


    public static ColumnConfig<LogModelSubColumn, LogModelPackageColumn> parentColumn() {
        ColumnConfig<LogModelSubColumn, LogModelPackageColumn> parentColumn = new ColumnConfig<LogModelSubColumn, LogModelPackageColumn>(PropertyUtils.LogModelSubColumnProperty.parentColumn(), 200, "parentColumn");
        parentColumn.setSortable(false);
        parentColumn.setMenuDisabled(true);
        return parentColumn;
    }

    public static ColumnConfig<LogModelSubColumn, LogModelColumnContent> content() {
        ColumnConfig<LogModelSubColumn, LogModelColumnContent> content = new ColumnConfig<LogModelSubColumn, LogModelColumnContent>(PropertyUtils.LogModelSubColumnProperty.content(), 200, "content");
        content.setSortable(false);
        content.setMenuDisabled(true);
        return content;
    }

    public static ColumnConfig<LogModelSubColumn, LogModelType> mtype() {
        ColumnConfig<LogModelSubColumn, LogModelType> mtype = new ColumnConfig<LogModelSubColumn, LogModelType>(PropertyUtils.LogModelSubColumnProperty.mtype(), 200, "mtype");
        mtype.setSortable(false);
        mtype.setMenuDisabled(true);
        return mtype;
    }

    public static ColumnConfig<LogModelSubColumn, Integer> id() {
        ColumnConfig<LogModelSubColumn, Integer> id = new ColumnConfig<LogModelSubColumn, Integer>(PropertyUtils.LogModelSubColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<LogModelSubColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<LogModelSubColumn, Date> lastUpdateTimeStamp = new ColumnConfig<LogModelSubColumn, Date>(PropertyUtils.LogModelSubColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<LogModelSubColumn, Date> createTime() {
        ColumnConfig<LogModelSubColumn, Date> createTime = new ColumnConfig<LogModelSubColumn, Date>(PropertyUtils.LogModelSubColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<LogModelSubColumn, String> createTimePropertyName() {
        ColumnConfig<LogModelSubColumn, String> createTimePropertyName = new ColumnConfig<LogModelSubColumn, String>(PropertyUtils.LogModelSubColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
