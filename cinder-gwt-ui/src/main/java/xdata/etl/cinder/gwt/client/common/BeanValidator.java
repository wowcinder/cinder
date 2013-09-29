
package xdata.etl.cinder.gwt.client.common;

import javax.validation.Validator;
import com.google.gwt.validation.client.GwtValidation;

@GwtValidation(groups = {
    javax.validation.groups.Default.class,
    xdata.etl.cinder.common.shared.groups.GWTChecks.class
}, value = {
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
    xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModel.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModel.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelColumn.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.LogModel.class,
    xdata.etl.cinder.logmodelmeta.shared.entity.LogModelVersion.class
})
public interface BeanValidator
    extends Validator
{


}
