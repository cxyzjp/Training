package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer10NumWays {
  public abstract int numWays(int n);

  private static class Solution1 extends Offer10NumWays {
    public int numWays(int n) {
      if (n == 0) {
        return 1;
      }
      if (n <= 2) {
        return n;
      }
      int c = 0;
      int p1 = 1;
      int p2 = 2;

      for (int i = 3; i <= n; i++) {
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

  private static void test(Offer10NumWays solution) {
    AssertUtils.assertEqual(2, solution.numWays(2));
    AssertUtils.assertEqual(1, solution.numWays(0));
    AssertUtils.assertEqual(21, solution.numWays(7));
  }
}
