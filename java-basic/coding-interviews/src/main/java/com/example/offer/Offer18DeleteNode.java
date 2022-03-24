package com.example.offer;

import com.example.common.ListNode;

public abstract class Offer18DeleteNode {
  public abstract ListNode deleteNode(ListNode head, int val);

  private static class Solution1 extends Offer18DeleteNode {
    public ListNode deleteNode(ListNode head, int val) {
      if (head == null) {
        return null;
      }
      if ((Integer) head.val == val) {
        return head.next;
      }
      ListNode pre = head;
      ListNode node = head.next;
      while (node != null) {
        if ((Integer) node.val == val) {
          pre.next = node.next;
          break;
        }
        pre = node;
        node = node.next;
      }
      return head;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer18DeleteNode solution) {
    // [4,1,9]
    solution.deleteNode(ListNode.newListNode(new Integer[]{4, 5, 1, 9}), 5).print();
    // [4,5,9]
    solution.deleteNode(ListNode.newListNode(new Integer[]{4, 5, 1, 9}), 1).print();
    System.out.println(solution.deleteNode(null, 1));
    solution.deleteNode(ListNode.newListNode(new Integer[]{4, 5, 1, 9}), 3).print();
  }
}
