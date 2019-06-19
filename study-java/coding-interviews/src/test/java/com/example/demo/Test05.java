package com.example.demo;

import java.util.Stack;

/**
 * description:
 * author: bowen
 * date: 2019/6/19
 */
public class Test05 {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        move(stack1, stack2);
        stack1.push(node);
        move(stack2, stack1);
    }

    public int pop() {
        if (stack1.empty()) {
            return -1;
        }
        return stack1.pop();
    }

    public void move(Stack<Integer> from, Stack<Integer> to) {
        if (from != null && !from.empty()) {
            to.push(from.pop());
            move(from, to);
        }
    }

    public static void main(String[] args) {
        Test05 t = new Test05();
        t.push(1);
        t.push(2);
        t.push(3);
        System.out.println(t.pop());
        t.push(4);
        System.out.println(t.pop());
        System.out.println(t.pop());
        System.out.println(t.pop());
    }
}
