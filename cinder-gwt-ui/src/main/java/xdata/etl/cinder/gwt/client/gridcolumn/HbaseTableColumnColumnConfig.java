
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;

public class HbaseTableColumnColumnConfig {


    public static ColumnConfig<HbaseTableColumn, String> shortname() {
        return new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.shortname(), 200, "shortname");
    }

    public static ColumnConfig<HbaseTableColumn, String> name() {
        return new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.name(), 200, "name");
    }

    public static ColumnConfig<HbaseTableColumn, Integer> id() {
        return new ColumnConfig<HbaseTableColumn, Integer>(PropertyUtils.HbaseTableColumnProperty.id(), 200, "id");
    }

    public static ColumnConfig<HbaseTableColumn, HbaseTableColumn.HbaseTableColumnType> type() {
        return new ColumnConfig<HbaseTableColumn, HbaseTableColumn.HbaseTableColumnType>(PropertyUtils.HbaseTableColumnProperty.type(), 200, "type");
    }

    public static ColumnConfig<HbaseTableColumn, String> desc() {
        return new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.desc(), 200, "desc");
    }

    public static ColumnConfig<HbaseTableColumn, HbaseTableVersion> version() {
        return new ColumnConfig<HbaseTableColumn, HbaseTableVersion>(PropertyUtils.HbaseTableColumnProperty.version(), 200, "version");
    }

    public static ColumnConfig<HbaseTableColumn, Integer> pos() {
        return new ColumnConfig<HbaseTableColumn, Integer>(PropertyUtils.HbaseTableColumnProperty.pos(), 200, "pos");
    }

    public static ColumnConfig<HbaseTableColumn, Date> lastUpdateTimeStamp() {
        return new ColumnConfig<HbaseTableColumn, Date>(PropertyUtils.HbaseTableColumnProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
    }

    public static ColumnConfig<HbaseTableColumn, Date> createTime() {
        return new ColumnConfig<HbaseTableColumn, Date>(PropertyUtils.HbaseTableColumnProperty.createTime(), 200, "createTime");
    }

    public static ColumnConfig<HbaseTableColumn, String> createTimePropertyName() {
        return new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.createTimePropertyName(), 200, "createTimePropertyName");
    }

}
