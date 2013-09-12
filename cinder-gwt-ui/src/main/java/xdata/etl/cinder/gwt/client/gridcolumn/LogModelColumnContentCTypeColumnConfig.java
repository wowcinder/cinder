
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.LogModelColumnContentCType;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public class LogModelColumnContentCTypeColumnConfig {


    public static ColumnConfig<LogModelColumnContentCType, Integer> pos() {
        ColumnConfig<LogModelColumnContentCType, Integer> pos = new ColumnConfig<LogModelColumnContentCType, Integer>(PropertyUtils.LogModelColumnContentCTypeProperty.pos(), 200, "pos");
        pos.setSortable(false);
        pos.setMenuDisabled(true);
        return pos;
    }

    public static ColumnConfig<LogModelColumnContentCType, LogModelType> mtype() {
        ColumnConfig<LogModelColumnContentCType, LogModelType> mtype = new ColumnConfig<LogModelColumnContentCType, LogModelType>(PropertyUtils.LogModelColumnContentCTypeProperty.mtype(), 200, "mtype");
        mtype.setSortable(false);
        mtype.setMenuDisabled(true);
        return mtype;
    }

    public static ColumnConfig<LogModelColumnContentCType, Integer> id() {
        ColumnConfig<LogModelColumnContentCType, Integer> id = new ColumnConfig<LogModelColumnContentCType, Integer>(PropertyUtils.LogModelColumnContentCTypeProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<LogModelColumnContentCType, Date> lastUpdateTimeStamp() {
        ColumnConfig<LogModelColumnContentCType, Date> lastUpdateTimeStamp = new ColumnConfig<LogModelColumnContentCType, Date>(PropertyUtils.LogModelColumnContentCTypeProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<LogModelColumnContentCType, Date> createTime() {
        ColumnConfig<LogModelColumnContentCType, Date> createTime = new ColumnConfig<LogModelColumnContentCType, Date>(PropertyUtils.LogModelColumnContentCTypeProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<LogModelColumnContentCType, String> createTimePropertyName() {
        ColumnConfig<LogModelColumnContentCType, String> createTimePropertyName = new ColumnConfig<LogModelColumnContentCType, String>(PropertyUtils.LogModelColumnContentCTypeProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
