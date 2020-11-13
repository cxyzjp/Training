package com.example.leetcode;

import com.example.common.ListNode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author zhangjianping
 * @date 2020/11/13
 **/
public class L21MergeTwoSortedLists {
  public static void main(String[] args) {
    L21MergeTwoSortedLists l21 = new L21MergeTwoSortedLists();

    ListNode<Integer> n3 = new ListNode<>(4);
    ListNode<Integer> n2 = new ListNode<>(2, n3);
    ListNode<Integer> n1 = new ListNode<>(1, n2);

    ListNode<Integer> n8 = new ListNode<>(6);
    ListNode<Integer> n7 = new ListNode<>(5, n8);
    ListNode<Integer> n6 = new ListNode<>(4, n7);
    ListNode<Integer> n5 = new ListNode<>(3, n6);
    ListNode<Integer> n4 = new ListNode<>(1, n5);
    ListNode<Integer> result = l21.mergeTwoLists(n1, n4);
    result.print();
  }

  public ListNode<Integer> mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
    ListNode<Integer> newNode = new ListNode<>();
    ListNode<Integer> firstNode = newNode;
    ListNode<Integer> l1Next = l1;
    ListNode<Integer> l2Next = l2;
    while (l1Next != null || l2Next != null) {
      if (l1Next != null && l2Next != null) {
        if (l1Next.val <= l2Next.val) {
          newNode.next = new ListNode<>(l1Next.val);
          l1Next = l1Next.next;
        } else {
          newNode.next = new ListNode<>(l2Next.val);
          l2Next = l2Next.next;
        }
        newNode = newNode.next;
      } else if (l1Next == null) {
        newNode.next = l2Next;
        break;
      } else {
        newNode.next = l1Next;
        break;
      }
    }
    return firstNode.next;
  }

}
