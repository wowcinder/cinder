
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.shared.entity.menu.MenuGroup;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

public class MenuGroupColumnConfig {


    public static ColumnConfig<MenuGroup, List<MenuNode>> nodes() {
        return new ColumnConfig<MenuGroup, List<MenuNode>>(PropertyUtils.MenuGroupProperty.nodes(), 200, "nodes");
    }

    public static ColumnConfig<MenuGroup, MenuNode> prev() {
        return new ColumnConfig<MenuGroup, MenuNode>(PropertyUtils.MenuGroupProperty.prev(), 200, "prev");
    }

    public static ColumnConfig<MenuGroup, String> name() {
        return new ColumnConfig<MenuGroup, String>(PropertyUtils.MenuGroupProperty.name(), 200, "name");
    }

    public static ColumnConfig<MenuGroup, MenuGroup> parent() {
        return new ColumnConfig<MenuGroup, MenuGroup>(PropertyUtils.MenuGroupProperty.parent(), 200, "parent");
    }

    public static ColumnConfig<MenuGroup, Integer> id() {
        return new ColumnConfig<MenuGroup, Integer>(PropertyUtils.MenuGroupProperty.id(), 200, "id");
    }

    public static ColumnConfig<MenuGroup, Date> lastUpdateTimeStamp() {
        return new ColumnConfig<MenuGroup, Date>(PropertyUtils.MenuGroupProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
    }

    public static ColumnConfig<MenuGroup, Date> createTime() {
        return new ColumnConfig<MenuGroup, Date>(PropertyUtils.MenuGroupProperty.createTime(), 200, "createTime");
    }

    public static ColumnConfig<MenuGroup, String> createTimePropertyName() {
        return new ColumnConfig<MenuGroup, String>(PropertyUtils.MenuGroupProperty.createTimePropertyName(), 200, "createTimePropertyName");
    }

}
