package com.example.concurrent.waitnotify;

public class Producer {
  public int i = 0;

  public synchronized void producer() {
    while (i > 3) {
      try {
        System.out.println("producer wait:" + i);
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    i++;
    System.out.println("producer:" + i);
    notifyAll();
  }

  public synchronized void consumer() {
    while (i <= 0) {
      try {
        System.out.println("consumer wait:" + i);
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    i--;
    System.out.println("consumer:" + i);
    notifyAll();
  }
}
