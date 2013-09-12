
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public class LogModelColumnContentColumnConfig {


    public static ColumnConfig<LogModelColumnContent, LogModelType> mtype() {
        ColumnConfig<LogModelColumnContent, LogModelType> mtype = new ColumnConfig<LogModelColumnContent, LogModelType>(PropertyUtils.LogModelColumnContentProperty.mtype(), 200, "mtype");
        mtype.setSortable(false);
        mtype.setMenuDisabled(true);
        return mtype;
    }

    public static ColumnConfig<LogModelColumnContent, Integer> id() {
        ColumnConfig<LogModelColumnContent, Integer> id = new ColumnConfig<LogModelColumnContent, Integer>(PropertyUtils.LogModelColumnContentProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<LogModelColumnContent, Date> lastUpdateTimeStamp() {
        ColumnConfig<LogModelColumnContent, Date> lastUpdateTimeStamp = new ColumnConfig<LogModelColumnContent, Date>(PropertyUtils.LogModelColumnContentProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<LogModelColumnContent, Date> createTime() {
        ColumnConfig<LogModelColumnContent, Date> createTime = new ColumnConfig<LogModelColumnContent, Date>(PropertyUtils.LogModelColumnContentProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<LogModelColumnContent, String> createTimePropertyName() {
        ColumnConfig<LogModelColumnContent, String> createTimePropertyName = new ColumnConfig<LogModelColumnContent, String>(PropertyUtils.LogModelColumnContentProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
