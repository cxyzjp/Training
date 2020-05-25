package com.fih.threadpool;

public class ThreadTest {
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getId() + " " + Thread.currentThread().getName());
		MyRunnable myRunnable = new MyRunnable();
		Thread thread = new Thread(myRunnable);
		thread.start();
	}

}

class MyRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getId() + " " +  Thread.currentThread().getName());
	}
}
