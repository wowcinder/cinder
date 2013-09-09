
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;

public class HbaseTableColumnConfig {


    public static ColumnConfig<HbaseTable, String> shortname() {
        return new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.shortname(), 200, "shortname");
    }

    public static ColumnConfig<HbaseTable, List<HbaseTableVersion>> versions() {
        return new ColumnConfig<HbaseTable, List<HbaseTableVersion>>(PropertyUtils.HbaseTableProperty.versions(), 200, "versions");
    }

    public static ColumnConfig<HbaseTable, String> name() {
        return new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.name(), 200, "name");
    }

    public static ColumnConfig<HbaseTable, Integer> id() {
        return new ColumnConfig<HbaseTable, Integer>(PropertyUtils.HbaseTableProperty.id(), 200, "id");
    }

    public static ColumnConfig<HbaseTable, String> desc() {
        return new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.desc(), 200, "desc");
    }

    public static ColumnConfig<HbaseTable, Date> lastUpdateTimeStamp() {
        return new ColumnConfig<HbaseTable, Date>(PropertyUtils.HbaseTableProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
    }

    public static ColumnConfig<HbaseTable, Date> createTime() {
        return new ColumnConfig<HbaseTable, Date>(PropertyUtils.HbaseTableProperty.createTime(), 200, "createTime");
    }

    public static ColumnConfig<HbaseTable, String> createTimePropertyName() {
        return new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.createTimePropertyName(), 200, "createTimePropertyName");
    }

}
