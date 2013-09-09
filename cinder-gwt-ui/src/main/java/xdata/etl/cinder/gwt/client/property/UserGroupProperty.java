
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.user.User;
import xdata.etl.cinder.shared.entity.user.UserGroup;

public interface UserGroupProperty
    extends PropertyAccess<UserGroup>
{


    public ValueProvider<UserGroup, List<User>> users();

    public ValueProvider<UserGroup, List<Authorize>> authorizes();

    public ValueProvider<UserGroup, String> name();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<UserGroup> key();

    public ValueProvider<UserGroup, Integer> id();

    public ValueProvider<UserGroup, Date> lastUpdateTimeStamp();

    public ValueProvider<UserGroup, Date> createTime();

    public ValueProvider<UserGroup, String> createTimePropertyName();

}
