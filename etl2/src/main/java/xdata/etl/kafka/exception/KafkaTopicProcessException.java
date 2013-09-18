package xdata.etl.kafka.exception;

public class KafkaTopicProcessException extends Exception {
	private static final long serialVersionUID = 2665837818158421401L;

	public KafkaTopicProcessException() {
	}

	public KafkaTopicProcessException(String msg) {
		super(msg);
	}

	public KafkaTopicProcessException(Throwable cause) {
		super(cause);
	}

	public KafkaTopicProcessException(String message, Throwable cause) {
		super(message, cause);
	}

}
