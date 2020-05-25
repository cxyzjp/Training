package com.example.practice.two;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class T14 {

    public static void main(String[] args) {
        T14 t = new T14();

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println(t.FindKthToTail2(n1, 2));
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        List<ListNode> list = new ArrayList<>();
        ListNode n = head;
        while (n != null) {
            list.add(n);
            n = n.next;
        }
        return list.get(list.size() - k);
    }

    public ListNode FindKthToTail2(ListNode head, int k) {
        ListNode p = head, q = head;
        int i = 0;
        while (q != null) {
            if (i >= k) {
                p = p.next;
            }
            i++;
            q = q.next;
        }
        return i < k ? null : p;
    }

}

