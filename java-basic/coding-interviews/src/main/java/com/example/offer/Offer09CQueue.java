package com.example.offer;

import java.util.Stack;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 */
public abstract class Offer09CQueue {
  public abstract void appendTail(int value);

  public abstract int deleteHead();

  public static class CQueue1 extends Offer09CQueue {
    Stack<Integer> s1, s2;

    public CQueue1() {
      s1 = new Stack<>(); // 主栈
      s2 = new Stack<>(); // 辅助栈
    }

    public void appendTail(int value) {
      s1.push(value);
    }

    public int deleteHead() {
      int head = -1;
      if (!s1.empty()) {
        while (!s1.empty()) {
          s2.push(s1.pop());
        }
        head = s2.pop();
        while (!s2.empty()) {
          s1.push(s2.pop());
        }
      }
      return head;
    }
  }

  private static class CQueue2 extends Offer09CQueue {
    Stack<Integer> s1, s2;

    public CQueue2() {
      s1 = new Stack<>(); // 入栈
      s2 = new Stack<>(); // 出栈
    }

    public void appendTail(int value) {
      s1.push(value);
    }

    public int deleteHead() {
      int head = -1;
      if (s2.isEmpty()) {
        while (!s1.isEmpty()) {
          s2.push(s1.pop());
        }
        if (!s2.isEmpty()) {
          head = s2.pop();
        }
      } else {
        head = s2.pop();
      }
      return head;
    }
  }

  public static void main(String[] args) {
    System.out.println("====== CQueue1");
    test(new CQueue1());
    System.out.println("====== CQueue2");
    test(new CQueue2());
  }

  private static void test(Offer09CQueue obj) {
    System.out.println(obj.deleteHead());
    obj.appendTail(1);
    obj.appendTail(2);
    obj.appendTail(3);
    System.out.println(obj.deleteHead());
    System.out.println(obj.deleteHead());
    System.out.println(obj.deleteHead());
    System.out.println(obj.deleteHead());
  }
}
