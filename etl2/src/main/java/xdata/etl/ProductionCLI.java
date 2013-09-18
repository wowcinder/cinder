package xdata.etl;

import org.springframework.context.support.AbstractXmlApplicationContext;

import xdata.etl.kafka.consumer.ConsumerBuilder;
import xdata.etl.kafka.consumer.IConsumer;

public class ProductionCLI {
	private static IConsumer consumer;

	public static void main(String[] args) throws InterruptedException {
		final AbstractXmlApplicationContext factory = RunableCLI.PRODUTION
				.getSpringCxt();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (consumer != null) {
					consumer.shutdown();
				}
				factory.close();
			}
		});
		final ConsumerBuilder builder = factory.getBean(ConsumerBuilder.class);
		consumer = builder.create();
		consumer.run();
	}
}
