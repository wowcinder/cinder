
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelSuperColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public class LogModelSuperColumnColumnConfig {


    public static ColumnConfig<LogModelSuperColumn, LogModelColumnContent> content() {
        ColumnConfig<LogModelSuperColumn, LogModelColumnContent> content = new ColumnConfig<LogModelSuperColumn, LogModelColumnContent>(PropertyUtils.LogModelSuperColumnProperty.content(), 200, "content");
        content.setSortable(false);
        content.setMenuDisabled(true);
        return content;
    }

    public static ColumnConfig<LogModelSuperColumn, LogModelType> mtype() {
        ColumnConfig<LogModelSuperColumn, LogModelType> mtype = new ColumnConfig<LogModelSuperColumn, LogModelType>(PropertyUtils.LogModelSuperColumnProperty.mtype(), 200, "mtype");
        mtype.setSortable(false);
        mtype.setMenuDisabled(true);
        return mtype;
    }

    public static ColumnConfig<LogModelSuperColumn, Integer> id() {
        ColumnConfig<LogModelSuperColumn, Integer> id = new ColumnConfig<LogModelSuperColumn, Integer>(PropertyUtils.LogModelSuperColumnProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<LogModelSuperColumn, Date> lastUpdateTimeStamp() {
        ColumnConfig<LogModelSuperColumn, Date> lastUpdateTimeStamp = new ColumnConfig<LogModelSuperColumn, Date>(PropertyUtils.LogModelSuperColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<LogModelSuperColumn, Date> createTime() {
        ColumnConfig<LogModelSuperColumn, Date> createTime = new ColumnConfig<LogModelSuperColumn, Date>(PropertyUtils.LogModelSuperColumnProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<LogModelSuperColumn, String> createTimePropertyName() {
        ColumnConfig<LogModelSuperColumn, String> createTimePropertyName = new ColumnConfig<LogModelSuperColumn, String>(PropertyUtils.LogModelSuperColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
