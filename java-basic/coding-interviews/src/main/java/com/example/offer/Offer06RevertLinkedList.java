package com.example.offer;

import com.example.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public abstract class Offer06RevertLinkedList {
  public int[] reversePrint(ListNode head) {
    return null;
  }

  private static class Solution1 extends Offer06RevertLinkedList {
    public int[] reversePrint(ListNode head) {
      Stack<Integer> stack = new Stack<>();
      int size = 0;
      while (head != null) {
        stack.push((Integer) head.val);
        size++;
        head = head.next;
      }
      int[] arr = new int[size];
      int i = 0;
      while (!stack.isEmpty()) {
        arr[i] = stack.pop();
        i++;
      }
      return arr;
    }
  }

  private static class Solution2 extends Offer06RevertLinkedList {
    List<Integer> list = new ArrayList<>();

    public int[] reversePrint(ListNode head) {
      reverse(head);
      int[] arr = new int[list.size()];
      int i = 0;
      for (Integer v : list) {
        arr[i] = v;
        i++;
      }
      return arr;
    }

    public void reverse(ListNode node) {
      if (node != null) {
        reverse(node.next);
        list.add((Integer) node.val);
      }
    }
  }

  private static class Solution3 extends Offer06RevertLinkedList {
    int[] arr;
    int i = 0;
    int j = 0;

    public int[] reversePrint(ListNode head) {
      reverse(head);
      return arr;
    }

    public void reverse(ListNode node) {
      if (node == null) {
        arr = new int[i];
        return;
      }
      i++;
      reverse(node.next);
      arr[j] = (Integer) node.val;
      j++;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
    test(new Solution3());
  }

  private static void test(Offer06RevertLinkedList revert) {
    ListNode<Integer> list = ListNode.newListNode(new Integer[]{1, 3, 2});
    list.print();
    System.out.println(Arrays.toString(revert.reversePrint(list)));
  }
}
