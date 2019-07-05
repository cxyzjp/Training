package com.example.demo;

/**
 * description:
 * 输入一个整数，输出该数二进制表示中1的个数。
 * author: bowen
 * date: 2019/7/1
 */
public class Test11 {

    /**
     * 思路：n=1往左位移相当于2的n次方。 target和n位与等于0，判断该位置是1.
     */
    public int NumberOf1(int n) {
        int flag = 1, count = 0;
        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /**
     * 思路：target和target-1位与，target最后一位1会变成0。
     */
    public int NumberOf2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        Test11 t = new Test11();
        System.out.println(t.NumberOf1(11));

        System.out.println(System.currentTimeMillis());
    }
}
