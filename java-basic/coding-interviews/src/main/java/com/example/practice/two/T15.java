package com.example.practice.two;

import com.example.common.ListNode;

/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class T15 {

    public static void main(String[] args) {
        T15 t = new T15();

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        long s = System.currentTimeMillis();
        System.out.println(t.ReverseList(n1));
        System.out.println(System.currentTimeMillis() - s);
    }

    public ListNode ReverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = null;
        ListNode next;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}

