
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaTopic;
import xdata.etl.cinder.logmodelmeta.shared.entity.kafka.KafkaWatchDogTopicSetting;

public interface KafkaTopicProperty
    extends PropertyAccess<KafkaTopic>
{


    public ValueProvider<KafkaTopic, KafkaTopic.KafkaTopicCharset> charset();

    public ValueProvider<KafkaTopic, List<KafkaWatchDogTopicSetting>> topicSettings();

    public ValueProvider<KafkaTopic, String> name();

    public ValueProvider<KafkaTopic, KafkaTopic.KafkaTopicStatus> status();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<KafkaTopic> key();

    public ValueProvider<KafkaTopic, Integer> id();

    public ValueProvider<KafkaTopic, Date> lastUpdateTimeStamp();

    public ValueProvider<KafkaTopic, Date> createTime();

    public ValueProvider<KafkaTopic, String> createTimePropertyName();

}
