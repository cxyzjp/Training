package com.fih.kafka.thread.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

	public MyRejectedExecutionHandler(){
		
	}
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		WorkerThread workerThread = (WorkerThread) r;
		String command = workerThread.getCommand();
		System.out.println(">>>>rejected:" + command);
	}

}
