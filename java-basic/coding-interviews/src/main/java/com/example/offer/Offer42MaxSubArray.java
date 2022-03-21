package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer42MaxSubArray {
  public abstract int maxSubArray(int[] nums);

  private static class Solution1 extends Offer42MaxSubArray {
    public int maxSubArray(int[] nums) {
      if (nums == null || nums.length == 0) {
        return -1;
      }
      int max = nums[0];
      int[] maxSums = new int[nums.length];
      maxSums[0] = nums[0];
      for (int i = 1; i < nums.length; i++) {
        if (maxSums[i - 1] < 0) {
          maxSums[i] = nums[i];
        } else {
          maxSums[i] = maxSums[i - 1] + nums[i];
        }
        max = Math.max(max, maxSums[i]);
      }
      return max;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer42MaxSubArray solution) {
    AssertUtils.assertEqual(6, solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
  }
}
