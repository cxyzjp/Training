package com.example.offer;

import com.example.common.ListNode;

public abstract class Offer25MergeTwoLists {
  public abstract ListNode mergeTwoLists(ListNode l1, ListNode l2);

  private static class Solution1 extends Offer25MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      if (l1 == null && l2 == null) {
        return null;
      }
      ListNode head = new ListNode();
      ListNode crr = head;
      while (true) {
        if (l1 == null) {
          crr.next = l2;
          break;
        }
        if (l2 == null) {
          crr.next = l1;
          break;
        }
        if ((Integer) l1.val < (Integer) l2.val) {
          crr.next = l1;
          l1 = l1.next;
        } else {
          crr.next = l2;
          l2 = l2.next;
        }
        crr = crr.next;
      }
      return head.next;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer25MergeTwoLists solution) {
    solution.mergeTwoLists(ListNode.newListNode(new Integer[]{1, 2, 3, 4, 5}),
        ListNode.newListNode(new Integer[]{1, 2, 3, 4, 5})).print();
    solution.mergeTwoLists(ListNode.newListNode(new Integer[]{1, 2, 4}),
        ListNode.newListNode(new Integer[]{1, 3, 4})).print();
  }
}
