package com.example.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 * author: bowen
 * date: 2019/4/25
 */
public class Test1 {
    private Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
        Test1 test = new Test1();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start 2");
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("interrupt 2");
        thread2.interrupt();
    }

    public void insert(Thread thread) {
        try {
            lock.lockInterruptibly();
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
            }
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }
}

class MyThread extends Thread {
    private Test1 test;
    public MyThread(Test1 test) {
        this.test = test;
    }
    @Override
    public void run() {
        test.insert(Thread.currentThread());
    }
}