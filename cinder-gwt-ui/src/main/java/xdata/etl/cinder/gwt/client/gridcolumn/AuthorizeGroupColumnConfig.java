
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.authorize.AuthorizeGroup;

public class AuthorizeGroupColumnConfig {


    public static ColumnConfig<AuthorizeGroup, Integer> displayOrder() {
        return new ColumnConfig<AuthorizeGroup, Integer>(PropertyUtils.AuthorizeGroupProperty.displayOrder(), 200, "displayOrder");
    }

    public static ColumnConfig<AuthorizeGroup, List<Authorize>> authorizes() {
        return new ColumnConfig<AuthorizeGroup, List<Authorize>>(PropertyUtils.AuthorizeGroupProperty.authorizes(), 200, "authorizes");
    }

    public static ColumnConfig<AuthorizeGroup, String> name() {
        return new ColumnConfig<AuthorizeGroup, String>(PropertyUtils.AuthorizeGroupProperty.name(), 200, "name");
    }

    public static ColumnConfig<AuthorizeGroup, Integer> id() {
        return new ColumnConfig<AuthorizeGroup, Integer>(PropertyUtils.AuthorizeGroupProperty.id(), 200, "id");
    }

    public static ColumnConfig<AuthorizeGroup, Date> lastUpdateTimeStamp() {
        return new ColumnConfig<AuthorizeGroup, Date>(PropertyUtils.AuthorizeGroupProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
    }

    public static ColumnConfig<AuthorizeGroup, Date> createTime() {
        return new ColumnConfig<AuthorizeGroup, Date>(PropertyUtils.AuthorizeGroupProperty.createTime(), 200, "createTime");
    }

    public static ColumnConfig<AuthorizeGroup, String> createTimePropertyName() {
        return new ColumnConfig<AuthorizeGroup, String>(PropertyUtils.AuthorizeGroupProperty.createTimePropertyName(), 200, "createTimePropertyName");
    }

}
