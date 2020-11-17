package com.example.leetcode;

/**
 * <pre>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * </pre>
 *
 * @author zhangjianping
 * @date 2020/11/17
 **/
public class L70ClimbStairs {
  public static void main(String[] args) {
    L70ClimbStairs l = new L70ClimbStairs();

    for (int i = 0; i < 10; i++) {
      int sum = l.climbStairs(i);
      System.out.println(i + "阶有" + sum + "种方法");
    }
    System.out.println(System.currentTimeMillis());
    System.out.println(l.climbStairs(999999999));
    System.out.println(System.currentTimeMillis());
  }

  public int climbStairs(int n) {
    if (n <= 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }

    int c1 = 1;
    int c2 = 0;
    int temp;
    for (int i = 2; i <= n; i++) {
      temp = c1;
      c1 = temp + c2;
      c2 = temp;
    }
    return c1 + c2;
  }
}
