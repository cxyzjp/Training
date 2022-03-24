package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer46TranslateNum {
  public abstract int translateNum(int num);

  private static class Solution1 extends Offer46TranslateNum {
    public int translateNum(int num) {
      if (num < 10) {
        return 1;
      }
      char[] chars = String.valueOf(num).toCharArray();
      int[] sum = new int[chars.length];
      sum[0] = 1;
      for (int i = 1; i < chars.length; i++) {
        if ('1' == chars[i - 1] || ('2' == chars[i - 1] && chars[i] <= '5')) {
          if (i == 1) {
            sum[i] = sum[i - 1] + 1;
          } else {
            sum[i] = sum[i - 1] + sum[i - 2];
          }
        } else {
          sum[i] = sum[i - 1];
        }
      }
      return sum[sum.length - 1];
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer46TranslateNum solution) {
//    AssertUtils.assertEqual(1, solution.translateNum(1));
//    AssertUtils.assertEqual(2, solution.translateNum(10));
    AssertUtils.assertEqual(5, solution.translateNum(12258));
  }
}
