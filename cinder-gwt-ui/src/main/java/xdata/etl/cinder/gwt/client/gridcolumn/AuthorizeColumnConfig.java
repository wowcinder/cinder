
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.authorize.AuthorizeGroup;
import xdata.etl.cinder.shared.entity.menu.Menu;
import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;

public class AuthorizeColumnConfig {


    public static ColumnConfig<Authorize, String> token() {
        return new ColumnConfig<Authorize, String>(PropertyUtils.AuthorizeProperty.token(), 200, "token");
    }

    public static ColumnConfig<Authorize, Integer> displayOrder() {
        return new ColumnConfig<Authorize, Integer>(PropertyUtils.AuthorizeProperty.displayOrder(), 200, "displayOrder");
    }

    public static ColumnConfig<Authorize, Set<Menu>> menus() {
        return new ColumnConfig<Authorize, Set<Menu>>(PropertyUtils.AuthorizeProperty.menus(), 200, "menus");
    }

    public static ColumnConfig<Authorize, Set<User>> users() {
        return new ColumnConfig<Authorize, Set<User>>(PropertyUtils.AuthorizeProperty.users(), 200, "users");
    }

    public static ColumnConfig<Authorize, Set<UserGroup>> userGroups() {
        return new ColumnConfig<Authorize, Set<UserGroup>>(PropertyUtils.AuthorizeProperty.userGroups(), 200, "userGroups");
    }

    public static ColumnConfig<Authorize, String> name() {
        return new ColumnConfig<Authorize, String>(PropertyUtils.AuthorizeProperty.name(), 200, "name");
    }

    public static ColumnConfig<Authorize, Integer> id() {
        return new ColumnConfig<Authorize, Integer>(PropertyUtils.AuthorizeProperty.id(), 200, "id");
    }

    public static ColumnConfig<Authorize, AuthorizeGroup> group() {
        return new ColumnConfig<Authorize, AuthorizeGroup>(PropertyUtils.AuthorizeProperty.group(), 200, "group");
    }

    public static ColumnConfig<Authorize, Date> lastUpdateTimeStamp() {
        return new ColumnConfig<Authorize, Date>(PropertyUtils.AuthorizeProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
    }

    public static ColumnConfig<Authorize, Date> createTime() {
        return new ColumnConfig<Authorize, Date>(PropertyUtils.AuthorizeProperty.createTime(), 200, "createTime");
    }

    public static ColumnConfig<Authorize, String> createTimePropertyName() {
        return new ColumnConfig<Authorize, String>(PropertyUtils.AuthorizeProperty.createTimePropertyName(), 200, "createTimePropertyName");
    }

}
