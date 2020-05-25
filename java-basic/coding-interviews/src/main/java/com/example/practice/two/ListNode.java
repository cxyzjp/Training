package com.example.practice.two;

public class ListNode {
    ListNode next = null;
    private int val;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }
}
