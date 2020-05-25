package com.fih.kafka.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableThreadPool {

	public static void main(String[] args) throws Exception {

		ThreadPoolExecutor executors = new ThreadPoolExecutor(2, 10, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
				new MyRejectedExecutionHandler());
		
		// 打印线程池状态
//		Thread monitorThread = new Thread(new MyMonitorThread(executors, 1));
//		monitorThread.start();

		long a = System.currentTimeMillis();
		// 执行
		for (int i = 1; i <= 20; i++) {
			System.out.println(i);
			
			Runnable worker = new WorkerThread("" + i);
			try {
				executors.execute(worker);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Thread.sleep(1000);
		}
		
		Thread.sleep(23000);
		long b = System.currentTimeMillis();
		System.out.println(b - a);
		
		for (int i = 21; i <= 30; i++) {
			System.out.println(i);
			
			Runnable worker = new WorkerThread("" + i);
			executors.execute(worker);
			
			Thread.sleep(1000);
		}
		
		// 关闭线程池
		executors.shutdown();
		while (!executors.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}

}