package xdata.etl.kafka.consumer;

public interface IConsumer {
	public void run();

	public void shutdown();

	public Integer getThreadCount();

	public void setThreadCount(Integer threadCount);
}
