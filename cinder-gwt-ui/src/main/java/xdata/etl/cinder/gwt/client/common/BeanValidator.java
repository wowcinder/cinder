package xdata.etl.cinder.gwt.client.common;

import javax.validation.Validator;
import com.google.gwt.validation.client.GwtValidation;

@GwtValidation({ xdata.etl.cinder.shared.entity.authorize.Authorize.class,
		xdata.etl.cinder.shared.entity.authorize.AuthorizeGroup.class,
		xdata.etl.cinder.shared.entity.menu.Menu.class,
		xdata.etl.cinder.shared.entity.menu.MenuGroup.class,
		xdata.etl.cinder.shared.entity.menu.MenuNode.class,
		xdata.etl.cinder.shared.entity.user.User.class,
		xdata.etl.cinder.shared.entity.user.UserGroup.class,
		xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable.class,
		xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn.class,
		xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion.class })
public interface BeanValidator extends Validator {

}
