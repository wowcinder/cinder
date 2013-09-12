
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.LogModelColumnContentJson;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public class LogModelColumnContentJsonColumnConfig {


    public static ColumnConfig<LogModelColumnContentJson, String> path() {
        ColumnConfig<LogModelColumnContentJson, String> path = new ColumnConfig<LogModelColumnContentJson, String>(PropertyUtils.LogModelColumnContentJsonProperty.path(), 200, "path");
        path.setSortable(false);
        path.setMenuDisabled(true);
        return path;
    }

    public static ColumnConfig<LogModelColumnContentJson, LogModelType> mtype() {
        ColumnConfig<LogModelColumnContentJson, LogModelType> mtype = new ColumnConfig<LogModelColumnContentJson, LogModelType>(PropertyUtils.LogModelColumnContentJsonProperty.mtype(), 200, "mtype");
        mtype.setSortable(false);
        mtype.setMenuDisabled(true);
        return mtype;
    }

    public static ColumnConfig<LogModelColumnContentJson, Integer> id() {
        ColumnConfig<LogModelColumnContentJson, Integer> id = new ColumnConfig<LogModelColumnContentJson, Integer>(PropertyUtils.LogModelColumnContentJsonProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<LogModelColumnContentJson, Date> lastUpdateTimeStamp() {
        ColumnConfig<LogModelColumnContentJson, Date> lastUpdateTimeStamp = new ColumnConfig<LogModelColumnContentJson, Date>(PropertyUtils.LogModelColumnContentJsonProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<LogModelColumnContentJson, Date> createTime() {
        ColumnConfig<LogModelColumnContentJson, Date> createTime = new ColumnConfig<LogModelColumnContentJson, Date>(PropertyUtils.LogModelColumnContentJsonProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<LogModelColumnContentJson, String> createTimePropertyName() {
        ColumnConfig<LogModelColumnContentJson, String> createTimePropertyName = new ColumnConfig<LogModelColumnContentJson, String>(PropertyUtils.LogModelColumnContentJsonProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
