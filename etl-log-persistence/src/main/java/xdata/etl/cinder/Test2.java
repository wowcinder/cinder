package xdata.etl.cinder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test2 {
	private static ExecutorService executor;

	public static void main(String[] args) throws InterruptedException {
		executor = Executors.newFixedThreadPool(10);
		executor.submit(new Runnable() {

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("shutdown");
			}
		});
		TimeUnit.SECONDS.sleep(1);
		executor.shutdown();

		System.out.println(executor.isTerminated());
		System.out.println(executor.isShutdown());
		
		TimeUnit.SECONDS.sleep(4);
		
		System.out.println(executor.isTerminated());
		System.out.println(executor.isShutdown());
	}
}
