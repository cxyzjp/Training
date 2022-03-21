package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer10Fib {
  public abstract int fib(int n);

  private static class Solution1 extends Offer10Fib {
    public int fib(int n) {
      if (n <= 1) {
        return n;
      }
      int c = 0;
      int p1 = 0;
      int p2 = 1;
      for (int i = 2; i <= n; i++) {
        c = (p1 + p2) % 1000000007;
        p1 = p2;
        p2 = c;
      }
      return c;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer10Fib solution) {
    AssertUtils.assertEqual(1, solution.fib(2));
    AssertUtils.assertEqual(5, solution.fib(5));
  }
}
