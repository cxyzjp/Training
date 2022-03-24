package com.example.offer;

import com.example.common.ListNode;

public abstract class Offer22GetKthFromEnd {
  public abstract ListNode getKthFromEnd(ListNode head, int k);

  private static class Solution1 extends Offer22GetKthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
      if (head == null) {
        return null;
      }
      ListNode node = head;
      int c = 0;
      while (node != null) {
        node = node.next;
        c++;
      }

      c = c - k;
      for (int i = 0; i < c; i++) {
        head = head.next;
      }
      return head;
    }

  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer22GetKthFromEnd solution) {
    // 4->5
    solution.getKthFromEnd(ListNode.newListNode(new Integer[]{1, 2, 3, 4, 5}), 2).print();
    // [4,5,9]
  }
}
