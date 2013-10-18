package xdata.etl.cinder.persistence.kafka.consumer;

public interface KafkaConsumerManager {
	public boolean run();

	public boolean shutdown() throws InterruptedException;

	public void commitOffsets();
}
