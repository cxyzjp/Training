package com.fih.threadpool.a;

/**
 * 单个线程
 */
public class ThreadRunnable implements Runnable {

	String name;

	public ThreadRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// 处理一个任务，这里的处理方式太简单了，仅仅是一个打印语句
		System.out.println("start .." + name);
		try {
			// 便于观察，等待一段时间
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
