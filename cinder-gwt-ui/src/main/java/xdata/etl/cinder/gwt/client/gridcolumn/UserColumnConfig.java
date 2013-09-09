
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;

public class UserColumnConfig {


    public static ColumnConfig<User, String> email() {
        return new ColumnConfig<User, String>(PropertyUtils.UserProperty.email(), 200, "email");
    }

    public static ColumnConfig<User, UserGroup> userGroup() {
        return new ColumnConfig<User, UserGroup>(PropertyUtils.UserProperty.userGroup(), 200, "userGroup");
    }

    public static ColumnConfig<User, List<Authorize>> extraAuthorizes() {
        return new ColumnConfig<User, List<Authorize>>(PropertyUtils.UserProperty.extraAuthorizes(), 200, "extraAuthorizes");
    }

    public static ColumnConfig<User, String> passwordPropertyName() {
        return new ColumnConfig<User, String>(PropertyUtils.UserProperty.passwordPropertyName(), 200, "passwordPropertyName");
    }

    public static ColumnConfig<User, Integer> id() {
        return new ColumnConfig<User, Integer>(PropertyUtils.UserProperty.id(), 200, "id");
    }

    public static ColumnConfig<User, String> password() {
        return new ColumnConfig<User, String>(PropertyUtils.UserProperty.password(), 200, "password");
    }

    public static ColumnConfig<User, Date> lastUpdateTimeStamp() {
        return new ColumnConfig<User, Date>(PropertyUtils.UserProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
    }

    public static ColumnConfig<User, Date> createTime() {
        return new ColumnConfig<User, Date>(PropertyUtils.UserProperty.createTime(), 200, "createTime");
    }

    public static ColumnConfig<User, String> createTimePropertyName() {
        return new ColumnConfig<User, String>(PropertyUtils.UserProperty.createTimePropertyName(), 200, "createTimePropertyName");
    }

}
