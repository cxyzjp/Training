package com.example.practice.one;

/**
 * description:
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 * <p>
 * author: bowen
 * date: 2019/6/19
 */
public class Test07 {
    public int Fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 0, next = 1, temp;
        for (int i = 2; i <= n; i++) {
            temp = pre + next;
            pre = next;
            next = temp;
        }
        return next;
    }

    public static void main(String[] args) {
        Test07 t = new Test07();
        System.out.println(t.Fibonacci(5));
    }
}
