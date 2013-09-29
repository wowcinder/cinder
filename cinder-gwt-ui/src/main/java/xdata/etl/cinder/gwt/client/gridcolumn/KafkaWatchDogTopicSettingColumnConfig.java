
package xdata.etl.cinder.gwt.client.gridcolumn;

import java.util.Date;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import xdata.etl.cinder.gwt.client.util.PropertyUtils;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDog;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public class KafkaWatchDogTopicSettingColumnConfig {


    public static ColumnConfig<KafkaWatchDogTopicSetting, KafkaWatchDog> server() {
        ColumnConfig<KafkaWatchDogTopicSetting, KafkaWatchDog> server = new ColumnConfig<KafkaWatchDogTopicSetting, KafkaWatchDog>(PropertyUtils.KafkaWatchDogTopicSettingProperty.server(), 200, "server");
        server.setSortable(false);
        server.setMenuDisabled(true);
        return server;
    }

    public static ColumnConfig<KafkaWatchDogTopicSetting, KafkaTopic> topic() {
        ColumnConfig<KafkaWatchDogTopicSetting, KafkaTopic> topic = new ColumnConfig<KafkaWatchDogTopicSetting, KafkaTopic>(PropertyUtils.KafkaWatchDogTopicSettingProperty.topic(), 200, "topic");
        topic.setSortable(false);
        topic.setMenuDisabled(true);
        return topic;
    }

    public static ColumnConfig<KafkaWatchDogTopicSetting, Integer> threadNum() {
        ColumnConfig<KafkaWatchDogTopicSetting, Integer> threadNum = new ColumnConfig<KafkaWatchDogTopicSetting, Integer>(PropertyUtils.KafkaWatchDogTopicSettingProperty.threadNum(), 200, "threadNum");
        threadNum.setSortable(false);
        threadNum.setMenuDisabled(true);
        return threadNum;
    }

    public static ColumnConfig<KafkaWatchDogTopicSetting, KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus> status() {
        ColumnConfig<KafkaWatchDogTopicSetting, KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus> status = new ColumnConfig<KafkaWatchDogTopicSetting, KafkaWatchDogTopicSetting.KafkaWatchDogTopicSettingStatus>(PropertyUtils.KafkaWatchDogTopicSettingProperty.status(), 200, "status");
        status.setSortable(false);
        status.setMenuDisabled(true);
        return status;
    }

    public static ColumnConfig<KafkaWatchDogTopicSetting, Integer> id() {
        ColumnConfig<KafkaWatchDogTopicSetting, Integer> id = new ColumnConfig<KafkaWatchDogTopicSetting, Integer>(PropertyUtils.KafkaWatchDogTopicSettingProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

    public static ColumnConfig<KafkaWatchDogTopicSetting, Date> lastUpdateTimeStamp() {
        ColumnConfig<KafkaWatchDogTopicSetting, Date> lastUpdateTimeStamp = new ColumnConfig<KafkaWatchDogTopicSetting, Date>(PropertyUtils.KafkaWatchDogTopicSettingProperty.lastUpdateTimeStamp(), 200, "lastUpdateTimeStamp");
        lastUpdateTimeStamp.setSortable(false);
        lastUpdateTimeStamp.setMenuDisabled(true);
        return lastUpdateTimeStamp;
    }

    public static ColumnConfig<KafkaWatchDogTopicSetting, Date> createTime() {
        ColumnConfig<KafkaWatchDogTopicSetting, Date> createTime = new ColumnConfig<KafkaWatchDogTopicSetting, Date>(PropertyUtils.KafkaWatchDogTopicSettingProperty.createTime(), 200, "createTime");
        createTime.setSortable(false);
        createTime.setMenuDisabled(true);
        return createTime;
    }

    public static ColumnConfig<KafkaWatchDogTopicSetting, String> createTimePropertyName() {
        ColumnConfig<KafkaWatchDogTopicSetting, String> createTimePropertyName = new ColumnConfig<KafkaWatchDogTopicSetting, String>(PropertyUtils.KafkaWatchDogTopicSettingProperty.createTimePropertyName(), 200, "createTimePropertyName");
        createTimePropertyName.setSortable(false);
        createTimePropertyName.setMenuDisabled(true);
        return createTimePropertyName;
    }

}
