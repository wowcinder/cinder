
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;

public class UserGroupColumnConfig {


    public static ColumnConfig<UserGroup, List<User>> users() {
        return new ColumnConfig<UserGroup, List<User>>(PropertyUtils.UserGroupProperty.users(), 200, "users");
    }

    public static ColumnConfig<UserGroup, List<Authorize>> authorizes() {
        return new ColumnConfig<UserGroup, List<Authorize>>(PropertyUtils.UserGroupProperty.authorizes(), 200, "authorizes");
    }

    public static ColumnConfig<UserGroup, String> name() {
        return new ColumnConfig<UserGroup, String>(PropertyUtils.UserGroupProperty.name(), 200, "name");
    }

    public static ColumnConfig<UserGroup, Integer> id() {
        return new ColumnConfig<UserGroup, Integer>(PropertyUtils.UserGroupProperty.id(), 200, "id");
    }

    public static ColumnConfig<UserGroup, Date> lastUpdateTimeStamp() {
        return new ColumnConfig<UserGroup, Date>(PropertyUtils.UserGroupProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
    }

    public static ColumnConfig<UserGroup, Date> createTime() {
        return new ColumnConfig<UserGroup, Date>(PropertyUtils.UserGroupProperty.createTime(), 200, "createTime");
    }

    public static ColumnConfig<UserGroup, String> createTimePropertyName() {
        return new ColumnConfig<UserGroup, String>(PropertyUtils.UserGroupProperty.createTimePropertyName(), 200, "createTimePropertyName");
    }

}
