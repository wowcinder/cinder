
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public class KafkaTopicColumnConfig {


    public static ColumnConfig<KafkaTopic, KafkaTopic.KafkaTopicCharset> charset() {
        ColumnConfig<KafkaTopic, KafkaTopic.KafkaTopicCharset> charset = new ColumnConfig<KafkaTopic, KafkaTopic.KafkaTopicCharset>(PropertyUtils.KafkaTopicProperty.charset(), 200, "charset");
        charset.setSortable(false);
        charset.setMenuDisabled(true);
        return charset;
    }

    public static ColumnConfig<KafkaTopic, List<KafkaWatchDogTopicSetting>> topicSettings() {
        ColumnConfig<KafkaTopic, List<KafkaWatchDogTopicSetting>> topicSettings = new ColumnConfig<KafkaTopic, List<KafkaWatchDogTopicSetting>>(PropertyUtils.KafkaTopicProperty.topicSettings(), 200, "topicSettings");
        topicSettings.setSortable(false);
        topicSettings.setMenuDisabled(true);
        return topicSettings;
    }

    public static ColumnConfig<KafkaTopic, String> name() {
        ColumnConfig<KafkaTopic, String> name = new ColumnConfig<KafkaTopic, String>(PropertyUtils.KafkaTopicProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<KafkaTopic, KafkaTopic.KafkaTopicStatus> status() {
        ColumnConfig<KafkaTopic, KafkaTopic.KafkaTopicStatus> status = new ColumnConfig<KafkaTopic, KafkaTopic.KafkaTopicStatus>(PropertyUtils.KafkaTopicProperty.status(), 200, "status");
        status.setSortable(false);
        status.setMenuDisabled(true);
        return status;
    }

    public static ColumnConfig<KafkaTopic, Integer> id() {
        ColumnConfig<KafkaTopic, Integer> id = new ColumnConfig<KafkaTopic, Integer>(PropertyUtils.KafkaTopicProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<KafkaTopic, Date> lastUpdateTimeStamp() {
        ColumnConfig<KafkaTopic, Date> lastUpdateTimeStamp = new ColumnConfig<KafkaTopic, Date>(PropertyUtils.KafkaTopicProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<KafkaTopic, Date> createTime() {
        ColumnConfig<KafkaTopic, Date> createTime = new ColumnConfig<KafkaTopic, Date>(PropertyUtils.KafkaTopicProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<KafkaTopic, String> createTimePropertyName() {
        ColumnConfig<KafkaTopic, String> createTimePropertyName = new ColumnConfig<KafkaTopic, String>(PropertyUtils.KafkaTopicProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
