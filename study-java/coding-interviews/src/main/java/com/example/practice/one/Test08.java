package com.example.practice.one;

/**
 * description:
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * <p>
 * author: bowen
 * date: 2019/6/21
 */
public class Test08 {

    public int JumpFloor(int target) {
        if (target <= 2) {
            return target;
        }
        int prePre = 1, pre = 2, t = 0;
        for (int i = 2; i < target; i++) {
            t = prePre + pre;
            prePre = pre;
            pre = t;
        }
        return t;
    }

    public static void main(String[] args) {
        Test08 t = new Test08();
        System.out.println(t.JumpFloor(5));
    }
}
