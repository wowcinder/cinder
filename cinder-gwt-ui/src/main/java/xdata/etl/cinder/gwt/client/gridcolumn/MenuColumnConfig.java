
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.menu.Menu;
import xdata.etl.cinder.shared.entity.menu.MenuGroup;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

public class MenuColumnConfig {


    public static ColumnConfig<Menu, String> token() {
        return new ColumnConfig<Menu, String>(PropertyUtils.MenuProperty.token(), 200, "token");
    }

    public static ColumnConfig<Menu, Authorize> requireAuthorize() {
        return new ColumnConfig<Menu, Authorize>(PropertyUtils.MenuProperty.requireAuthorize(), 200, "requireAuthorize");
    }

    public static ColumnConfig<Menu, MenuNode> prev() {
        return new ColumnConfig<Menu, MenuNode>(PropertyUtils.MenuProperty.prev(), 200, "prev");
    }

    public static ColumnConfig<Menu, String> name() {
        return new ColumnConfig<Menu, String>(PropertyUtils.MenuProperty.name(), 200, "name");
    }

    public static ColumnConfig<Menu, MenuGroup> parent() {
        return new ColumnConfig<Menu, MenuGroup>(PropertyUtils.MenuProperty.parent(), 200, "parent");
    }

    public static ColumnConfig<Menu, Integer> id() {
        return new ColumnConfig<Menu, Integer>(PropertyUtils.MenuProperty.id(), 200, "id");
    }

    public static ColumnConfig<Menu, Date> lastUpdateTimeStamp() {
        return new ColumnConfig<Menu, Date>(PropertyUtils.MenuProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
    }

    public static ColumnConfig<Menu, Date> createTime() {
        return new ColumnConfig<Menu, Date>(PropertyUtils.MenuProperty.createTime(), 200, "createTime");
    }

    public static ColumnConfig<Menu, String> createTimePropertyName() {
        return new ColumnConfig<Menu, String>(PropertyUtils.MenuProperty.createTimePropertyName(), 200, "createTimePropertyName");
    }

}
