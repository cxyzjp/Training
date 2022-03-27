package com.example.offer;

import com.example.common.ListNode;

public abstract class Offer52GetIntersectionNode {
  public abstract ListNode getIntersectionNode(ListNode headA, ListNode headB);

  private static class Solution1 extends Offer52GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
      if (headA == null || headB == null) {
        return null;
      }
      ListNode a = headA;
      ListNode b = headB;
      while (a != b) {
        a = a == null ? headB : a.next;
        b = b == null ? headA : b.next;
      }
      return a;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer52GetIntersectionNode solution) {
    // 8
//    solution.getIntersectionNode(ListNode.newListNode(new Integer[]{4, 1, 8, 4, 5}), ListNode.newListNode(new Integer[]{5, 0, 1, 8, 4, 5})).print();
    // null
//    solution.getIntersectionNode(ListNode.newListNode(new Integer[]{2, 6, 4}),
//        ListNode.newListNode(new Integer[]{1, 5})).print();
  }
}
