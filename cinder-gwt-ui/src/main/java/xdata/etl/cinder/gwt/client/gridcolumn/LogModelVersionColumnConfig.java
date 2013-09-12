
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.type.LogModelType;

public class LogModelVersionColumnConfig {


    public static ColumnConfig<LogModelVersion, String> desc() {
        ColumnConfig<LogModelVersion, String> desc = new ColumnConfig<LogModelVersion, String>(PropertyUtils.LogModelVersionProperty.desc(), 200, "desc");
        desc.setSortable(false);
        desc.setMenuDisabled(true);
        return desc;
    }

    public static ColumnConfig<LogModelVersion, String> version() {
        ColumnConfig<LogModelVersion, String> version = new ColumnConfig<LogModelVersion, String>(PropertyUtils.LogModelVersionProperty.version(), 200, "version");
        version.setSortable(false);
        version.setMenuDisabled(true);
        return version;
    }

    public static ColumnConfig<LogModelVersion, LogModel> model() {
        ColumnConfig<LogModelVersion, LogModel> model = new ColumnConfig<LogModelVersion, LogModel>(PropertyUtils.LogModelVersionProperty.model(), 200, "model");
        model.setSortable(false);
        model.setMenuDisabled(true);
        return model;
    }

    public static ColumnConfig<LogModelVersion, List<LogModelColumn>> columns() {
        ColumnConfig<LogModelVersion, List<LogModelColumn>> columns = new ColumnConfig<LogModelVersion, List<LogModelColumn>>(PropertyUtils.LogModelVersionProperty.columns(), 200, "columns");
        columns.setSortable(false);
        columns.setMenuDisabled(true);
        return columns;
    }

    public static ColumnConfig<LogModelVersion, LogModelType> mtype() {
        ColumnConfig<LogModelVersion, LogModelType> mtype = new ColumnConfig<LogModelVersion, LogModelType>(PropertyUtils.LogModelVersionProperty.mtype(), 200, "mtype");
        mtype.setSortable(false);
        mtype.setMenuDisabled(true);
        return mtype;
    }

    public static ColumnConfig<LogModelVersion, Integer> id() {
        ColumnConfig<LogModelVersion, Integer> id = new ColumnConfig<LogModelVersion, Integer>(PropertyUtils.LogModelVersionProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<LogModelVersion, Date> lastUpdateTimeStamp() {
        ColumnConfig<LogModelVersion, Date> lastUpdateTimeStamp = new ColumnConfig<LogModelVersion, Date>(PropertyUtils.LogModelVersionProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<LogModelVersion, Date> createTime() {
        ColumnConfig<LogModelVersion, Date> createTime = new ColumnConfig<LogModelVersion, Date>(PropertyUtils.LogModelVersionProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<LogModelVersion, String> createTimePropertyName() {
        ColumnConfig<LogModelVersion, String> createTimePropertyName = new ColumnConfig<LogModelVersion, String>(PropertyUtils.LogModelVersionProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
