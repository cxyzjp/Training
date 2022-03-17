package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer53MissingNumber {
  public abstract int missingNumber(int[] nums);

  private static class Solution1 extends Offer53MissingNumber {
    public int missingNumber(int[] nums) {
      int i = 0;
      for (; i < nums.length; i++) {
        if (i != nums[i]) {
          return i;
        }
      }
      return i;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer53MissingNumber solution) {
    AssertUtils.assertEqual(1, solution.missingNumber(new int[]{0}));
    AssertUtils.assertEqual(2, solution.missingNumber(new int[]{0, 1, 3}));
    AssertUtils.assertEqual(8, solution.missingNumber(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9}));
  }
}
