
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.menu.Menu;
import xdata.etl.cinder.shared.entity.menu.MenuGroup;
import xdata.etl.cinder.shared.entity.menu.MenuNode;

public interface MenuProperty
    extends PropertyAccess<Menu>
{


    public ValueProvider<Menu, String> token();

    public ValueProvider<Menu, Authorize> requireAuthorize();

    public ValueProvider<Menu, MenuNode> prev();

    public ValueProvider<Menu, String> name();

    public ValueProvider<Menu, MenuGroup> parent();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<Menu> key();

    public ValueProvider<Menu, Integer> id();

    public ValueProvider<Menu, Date> lastUpdateTimeStamp();

    public ValueProvider<Menu, Date> createTime();

    public ValueProvider<Menu, String> createTimePropertyName();

}
