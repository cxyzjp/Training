package com.example.offer;

import com.example.utils.AssertUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
public abstract class Offer30MinStack {

  public abstract void push(int x);

  public abstract void pop();

  public abstract int top();

  public abstract int min();

  static class MinStack1 extends Offer30MinStack {
    List<Integer> list;

    public MinStack1() {
      list = new ArrayList<>();
    }

    public void push(int x) {
      list.add(x);
    }

    public void pop() {
      if (!list.isEmpty()) {
        list.remove(list.size() - 1);
      }
    }

    public int top() {
      if (list.isEmpty()) {
        throw new IllegalArgumentException();
      }
      return list.get(list.size() - 1);
    }

    public int min() {
      if (list.isEmpty()) {
        throw new IllegalArgumentException();
      }
      int m = list.get(0);
      for (Integer i : list) {
        if (i < m) {
          m = i;
        }
      }
      return m;
    }
  }

  static class MinStack2 extends Offer30MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack2() {
      stack = new Stack<>();
      minStack = new Stack<>();
    }

    public void push(int x) {
      stack.push(x);
      if (minStack.isEmpty() || x <= minStack.peek()) {
        minStack.push(x);
      }
    }

    public void pop() {
      Integer pop = stack.pop();
      if (!minStack.isEmpty() && pop.equals(minStack.peek())) {
        minStack.pop();
      }
    }

    public int top() {
      return stack.peek();
    }

    public int min() {
      return minStack.peek();
    }
  }

  public static void main(String[] args) {
    test(new MinStack1());
    test(new MinStack2());
  }

  private static void test(Offer30MinStack stack) {
    stack.push(-2);
    stack.push(0);
    stack.push(-3);
    int min = stack.min();
    AssertUtils.assertEqual(-3, min);
    stack.pop();
    int top = stack.top();
    AssertUtils.assertEqual(0, top);
    int min1 = stack.min();
    AssertUtils.assertEqual(-2, min1);
  }

}
