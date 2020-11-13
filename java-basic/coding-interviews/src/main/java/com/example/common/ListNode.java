package com.example.common;

import java.util.Objects;

/**
 * ListNode
 *
 * @author zhangjianping
 * @date 2020/11/13
 **/
public class ListNode<T> {
  public T val;
  public ListNode<T> next;

  public ListNode() {
  }

  public ListNode(T val) {
    this.val = val;
  }

  public ListNode(T val, ListNode<T> next) {
    this.val = val;
    this.next = next;
  }

  @Override
  public String toString() {
    return Objects.toString(val);
  }

  public void print() {
    StringBuilder result = new StringBuilder();
    ListNode<T> nextNode = this;
    do {
      result.append(nextNode.toString());
      nextNode = nextNode.next;
    } while (nextNode != null);
    System.out.println(result);
  }
}
