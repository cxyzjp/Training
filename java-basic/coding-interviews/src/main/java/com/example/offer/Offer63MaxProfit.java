package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer63MaxProfit {
  public abstract int maxProfit(int[] prices);

  private static class Solution1 extends Offer63MaxProfit {
    public int maxProfit(int[] prices) {
      int max = 0;
      for (int i = 0; i < prices.length - 1; i++) {
        for (int j = i + 1; j < prices.length; j++) {
          if (prices[j] > prices[i] && (prices[j] - prices[i]) > max) {
            max = prices[j] - prices[i];
          }
        }
      }
      return max;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer63MaxProfit solution) {
    AssertUtils.assertEqual(5, solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    AssertUtils.assertEqual(0, solution.maxProfit(new int[]{7, 6, 4, 3, 1}));
  }
}
