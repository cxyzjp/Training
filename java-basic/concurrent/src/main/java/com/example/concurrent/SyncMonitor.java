package com.example.concurrent;

/**
 * 反编译：javap -v SyncMonitor.class
 *
 * @author zhangjianping
 * @date 2021/3/3
 **/
public class SyncMonitor {

  /**
   * 3: monitorenter
   * ...
   * 19: monitorexit
   * ...
   */
  public void syncBlock() {
    synchronized (this) {
      System.out.println("sync block");
    }
  }

  /**
   * flags: ACC_PUBLIC, ACC_SYNCHRONIZED
   */
  public synchronized void syncMethod() {
    System.out.println("sync method");
  }
}
