
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public class LogModelColumnColumnConfig {


    public static ColumnConfig<LogModelColumn, LogModelVersion> version() {
        ColumnConfig<LogModelColumn, LogModelVersion> version = new ColumnConfig<LogModelColumn, LogModelVersion>(PropertyUtils.LogModelColumnProperty.version(), 200, "version");
        version.setSortable(false);
        version.setMenuDisabled(true);
        return version;
    }

    public static ColumnConfig<LogModelColumn, LogModelColumnContent> content() {
        ColumnConfig<LogModelColumn, LogModelColumnContent> content = new ColumnConfig<LogModelColumn, LogModelColumnContent>(PropertyUtils.LogModelColumnProperty.content(), 200, "content");
        content.setSortable(false);
        content.setMenuDisabled(true);
        return content;
    }

    public static ColumnConfig<LogModelColumn, LogModelType> mtype() {
        ColumnConfig<LogModelColumn, LogModelType> mtype = new ColumnConfig<LogModelColumn, LogModelType>(PropertyUtils.LogModelColumnProperty.mtype(), 200, "mtype");
        mtype.setSortable(false);
        mtype.setMenuDisabled(true);
        return mtype;
    }

    public static ColumnConfig<LogModelColumn, Integer> id() {
        ColumnConfig<LogModelColumn, Integer> id = new ColumnConfig<LogModelColumn, Integer>(PropertyUtils.LogModelColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<LogModelColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<LogModelColumn, Date> lastUpdateTimeStamp = new ColumnConfig<LogModelColumn, Date>(PropertyUtils.LogModelColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<LogModelColumn, Date> createTime() {
        ColumnConfig<LogModelColumn, Date> createTime = new ColumnConfig<LogModelColumn, Date>(PropertyUtils.LogModelColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<LogModelColumn, String> createTimePropertyName() {
        ColumnConfig<LogModelColumn, String> createTimePropertyName = new ColumnConfig<LogModelColumn, String>(PropertyUtils.LogModelColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
