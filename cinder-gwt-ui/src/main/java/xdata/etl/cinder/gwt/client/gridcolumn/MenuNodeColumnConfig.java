
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.shared.entity.menu.MenuGroup;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

public class MenuNodeColumnConfig {


    public static ColumnConfig<MenuNode, MenuNode> prev() {
        return new ColumnConfig<MenuNode, MenuNode>(PropertyUtils.MenuNodeProperty.prev(), 200, "prev");
    }

    public static ColumnConfig<MenuNode, String> name() {
        return new ColumnConfig<MenuNode, String>(PropertyUtils.MenuNodeProperty.name(), 200, "name");
    }

    public static ColumnConfig<MenuNode, MenuGroup> parent() {
        return new ColumnConfig<MenuNode, MenuGroup>(PropertyUtils.MenuNodeProperty.parent(), 200, "parent");
    }

    public static ColumnConfig<MenuNode, Integer> id() {
        return new ColumnConfig<MenuNode, Integer>(PropertyUtils.MenuNodeProperty.id(), 200, "id");
    }

    public static ColumnConfig<MenuNode, Date> lastUpdateTimeStamp() {
        return new ColumnConfig<MenuNode, Date>(PropertyUtils.MenuNodeProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
    }

    public static ColumnConfig<MenuNode, Date> createTime() {
        return new ColumnConfig<MenuNode, Date>(PropertyUtils.MenuNodeProperty.createTime(), 200, "createTime");
    }

    public static ColumnConfig<MenuNode, String> createTimePropertyName() {
        return new ColumnConfig<MenuNode, String>(PropertyUtils.MenuNodeProperty.createTimePropertyName(), 200, "createTimePropertyName");
    }

}
