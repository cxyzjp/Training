package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer11MinArray {
  public abstract int minArray(int[] numbers);

  private static class Solution1 extends Offer11MinArray {
    public int minArray(int[] nums) {
      if (nums == null || nums.length == 0) {
        return -1;
      }
      int min = nums[0];
      for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i] > nums[i + 1]) {
          min = nums[i + 1];
          break;
        }
      }
      return min;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer11MinArray solution) {
    AssertUtils.assertEqual(1, solution.minArray(new int[]{3,4,5,1,2}));
    AssertUtils.assertEqual(0, solution.minArray(new int[]{2,2,2,0,1}));
  }
}
