
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public class LogModelColumnConfig {


    public static ColumnConfig<LogModel, List<LogModelVersion>> versions() {
        ColumnConfig<LogModel, List<LogModelVersion>> versions = new ColumnConfig<LogModel, List<LogModelVersion>>(PropertyUtils.LogModelProperty.versions(), 200, "versions");
        versions.setSortable(false);
        versions.setMenuDisabled(true);
        return versions;
    }

    public static ColumnConfig<LogModel, String> name() {
        ColumnConfig<LogModel, String> name = new ColumnConfig<LogModel, String>(PropertyUtils.LogModelProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<LogModel, String> desc() {
        ColumnConfig<LogModel, String> desc = new ColumnConfig<LogModel, String>(PropertyUtils.LogModelProperty.desc(), 200, "desc");
        desc.setSortable(false);
        desc.setMenuDisabled(true);
        return desc;
    }

    public static ColumnConfig<LogModel, LogModelType> mtype() {
        ColumnConfig<LogModel, LogModelType> mtype = new ColumnConfig<LogModel, LogModelType>(PropertyUtils.LogModelProperty.mtype(), 200, "mtype");
        mtype.setSortable(false);
        mtype.setMenuDisabled(true);
        return mtype;
    }

    public static ColumnConfig<LogModel, Integer> id() {
        ColumnConfig<LogModel, Integer> id = new ColumnConfig<LogModel, Integer>(PropertyUtils.LogModelProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<LogModel, Date> lastUpdateTimeStamp() {
        ColumnConfig<LogModel, Date> lastUpdateTimeStamp = new ColumnConfig<LogModel, Date>(PropertyUtils.LogModelProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<LogModel, Date> createTime() {
        ColumnConfig<LogModel, Date> createTime = new ColumnConfig<LogModel, Date>(PropertyUtils.LogModelProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<LogModel, String> createTimePropertyName() {
        ColumnConfig<LogModel, String> createTimePropertyName = new ColumnConfig<LogModel, String>(PropertyUtils.LogModelProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
