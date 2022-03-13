package com.example.concurrent.waitnotify;

public class ProductFactory {
  private static final Producer p = new Producer();

  private static class A extends Thread {
    @Override
    public void run() {
      p.producer();
    }
  }

  private static class C extends Thread {
    @Override
    public void run() {
      p.consumer();
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      new A().start();
    }
    for (int i = 0; i < 20; i++) {
      new C().start();
    }
  }
}
