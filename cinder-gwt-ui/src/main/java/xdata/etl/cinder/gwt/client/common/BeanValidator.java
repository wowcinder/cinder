
package xdata.etl.cinder.gwt.client.common;

import javax.validation.Validator;
import com.google.gwt.validation.client.GwtValidation;

@GwtValidation({
    xdata.etl.cinder.shared.entity.authorize.Authorize.class,
    xdata.etl.cinder.shared.entity.authorize.AuthorizeGroup.class,
    xdata.etl.cinder.shared.entity.menu.Menu.class,
    xdata.etl.cinder.shared.entity.menu.MenuGroup.class,
    xdata.etl.cinder.shared.entity.menu.MenuNode.class,
    xdata.etl.cinder.shared.entity.user.User.class,
    xdata.etl.cinder.shared.entity.user.UserGroup.class,
    xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable.class,
    xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn.class,
    xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumn.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelColumnContent.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelPackageColumn.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelSubColumn.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.base.column.LogModelSuperColumn.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.c.LogModelColumnContentCType.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.json.LogModelColumnContentJson.class
})
public interface BeanValidator
    extends Validator
{


}
