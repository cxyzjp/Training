package com.example.practice.one;

import java.util.ArrayList;

/**
 * description: 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 * author: bowen
 * date: 2019/6/17
 */
public class Test03 {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private ListNode getListNode() {
        ListNode n3 = new ListNode(3, null);
        ListNode n2 = new ListNode(2, n3);
        return new ListNode(1, n2);
    }

    public static void main(String[] args) {
        Test03 t = new Test03();
        System.out.println(t.printListFromTailToHead(t.getListNode()));
    }

    // 递归
    private ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }

    // 轮询加入List，倒序List
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        int size = list.size();
        for (int i = 0; i < size / 2; i++) {
            Integer temp = list.get(i);
            list.set(i, list.get(size - 1 - i));
            list.set(size - 1 - i, temp);
        }
        return list;
    }

}
