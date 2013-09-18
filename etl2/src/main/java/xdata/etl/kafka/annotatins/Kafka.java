package xdata.etl.kafka.annotatins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import xdata.etl.kafka.process.KafkaTopicProcess;
import xdata.etl.kafka.process.StartWithStampProcess;
import xdata.etl.kafka.transform.KafkaTransformer;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Kafka {
	String topic();

	Class<? extends KafkaTransformer> transformer();

	Class<? extends KafkaTopicProcess> process() default StartWithStampProcess.class;

	boolean isSnap() default false;
}
