
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;

public class HbaseTableVersionColumnConfig {


    public static ColumnConfig<HbaseTableVersion, Integer> id() {
        return new ColumnConfig<HbaseTableVersion, Integer>(PropertyUtils.HbaseTableVersionProperty.id(), 200, "id");
    }

    public static ColumnConfig<HbaseTableVersion, HbaseTable> table() {
        return new ColumnConfig<HbaseTableVersion, HbaseTable>(PropertyUtils.HbaseTableVersionProperty.table(), 200, "table");
    }

    public static ColumnConfig<HbaseTableVersion, String> desc() {
        return new ColumnConfig<HbaseTableVersion, String>(PropertyUtils.HbaseTableVersionProperty.desc(), 200, "desc");
    }

    public static ColumnConfig<HbaseTableVersion, String> version() {
        return new ColumnConfig<HbaseTableVersion, String>(PropertyUtils.HbaseTableVersionProperty.version(), 200, "version");
    }

    public static ColumnConfig<HbaseTableVersion, List<HbaseTableColumn>> columns() {
        return new ColumnConfig<HbaseTableVersion, List<HbaseTableColumn>>(PropertyUtils.HbaseTableVersionProperty.columns(), 200, "columns");
    }

    public static ColumnConfig<HbaseTableVersion, Date> lastUpdateTimeStamp() {
        return new ColumnConfig<HbaseTableVersion, Date>(PropertyUtils.HbaseTableVersionProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
    }

    public static ColumnConfig<HbaseTableVersion, Date> createTime() {
        return new ColumnConfig<HbaseTableVersion, Date>(PropertyUtils.HbaseTableVersionProperty.createTime(), 200, "createTime");
    }

    public static ColumnConfig<HbaseTableVersion, String> createTimePropertyName() {
        return new ColumnConfig<HbaseTableVersion, String>(PropertyUtils.HbaseTableVersionProperty.createTimePropertyName(), 200, "createTimePropertyName");
    }

}
