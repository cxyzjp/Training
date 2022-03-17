package com.example.offer;

import com.example.common.ListNode;

import java.util.Stack;

public abstract class Offer24 {
  public abstract ListNode reverseList(ListNode head);

  public static class Solution1 extends Offer24 {
    Stack<Integer> stack = new Stack<>();

    public ListNode reverseList(ListNode head) {
      while (head != null) {
        stack.push((Integer) head.val);
        head = head.next;
      }

      if (stack.isEmpty()) {
        return null;
      }
      ListNode newHead = new ListNode(stack.pop());
      ListNode node = newHead;
      while (!stack.isEmpty()) {
        node.next = new ListNode(stack.pop());
        node = node.next;
      }
      return newHead;
    }
  }

  public static class Solution2 extends Offer24 {
    ListNode node = new ListNode();
    ListNode crr = node;

    public ListNode reverseList(ListNode head) {
      revers(head);
      return node.next;
    }

    private void revers(ListNode head) {
      if (head == null) {
        return;
      }
      revers(head.next);
      crr.next = new ListNode(head.val);
      crr = crr.next;
    }
  }

  public static class Solution3 extends Offer24 {

    public ListNode reverseList(ListNode head) {
      ListNode pre = null;
      ListNode crr = head;
      while (crr != null) {
        ListNode next = crr.next;
        crr.next = pre;
        pre = crr;
        crr = next;
      }
      return pre;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
    test(new Solution3());
  }

  private static void test(Offer24 solution) {
    ListNode<Integer> head = ListNode.newListNode(new Integer[]{1, 2, 3, 4, 5});
    head.print();
    ListNode node = solution.reverseList(head);
    node.print();
  }
}
