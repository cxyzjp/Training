package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer61IsStraight {
  public abstract boolean isStraight(int[] nums);

  private static class Solution1 extends Offer61IsStraight {
    public boolean isStraight(int[] nums) {
      for (int i = 0; i < nums.length - 1; i++) {
        for (int j = i; j < nums.length; j++) {
          if (nums[i] > nums[j]) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
          }
        }
      }
      int c0 = 0;
      for (int num : nums) {
        if (num == 0) {
          c0++;
        } else {
          break;
        }
      }
      boolean res = true;
      for (int i = c0 + 1; i < nums.length; i++) {
        int pre = nums[i - 1];
        if (nums[i] - pre != 1) {
          boolean s = false;
          while (c0 > 0) {
            pre++;
            c0--;
            if (nums[i] - pre == 1) {
              s = true;
              break;
            }
          }
          res = s;
          if (!res) {
            break;
          }
        }
      }
      return res;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer61IsStraight solution) {
    AssertUtils.assertEqual(true, solution.isStraight(new int[]{3, 4, 5, 1, 2}));
    AssertUtils.assertEqual(false, solution.isStraight(new int[]{2, 2, 2, 0, 1}));
  }
}
