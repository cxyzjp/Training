package com.fih.threadpool.a;

/**
 * http://blog.csdn.net/owen_william/article/details/70664716
 */
public class ThreadTest {

	public static void main(String[] args) throws Exception {
		int workQueneSize = 2;
		int coreSize = 4;
		int maxSize = 10;

		// 创建线程池
		ThreadPool tp = new ThreadPool(workQueneSize, coreSize, maxSize, ThreadPool.ARRAY_QUEUE);
		
		for (int i = 0; i < 10; i++) {
			tp.execute(new ThreadRunnable("num:" + i));
		}
		
		Thread.sleep(10000);
		tp.shutdown();
	}
	
}
