package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer53Search {
  public abstract int search(int[] nums, int target);

  private static class Solution1 extends Offer53Search {
    public int search(int[] nums, int target) {
      int count = 0;
      for (int num : nums) {
        if (num < target) {
          continue;
        }
        if (target == num) {
          count++;
          continue;
        }
        break;
      }
      return count;
    }
  }

  private static class Solution2 extends Offer53Search {
    public int search(int[] nums, int target) {
      // 找右边界
      int i = 0;
      int j = nums.length - 1;
      while (i <= j) {
        int m = (i + j) / 2;
        if (nums[m] <= target) {
          i = m + 1;
        } else {
          j = m - 1;
        }
      }
      if (j > 0 && nums[j] != target) {
        return 0;
      }
      int right = i;
      i = 0;
      j = nums.length - 1;
      while (i <= j) {
        int m = (i + j) / 2;
        if (nums[m] < target) {
          i = m + 1;
        } else {
          j = m - 1;
        }
      }
      return right - j - 1;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer53Search solution) {
    AssertUtils.assertEqual(2, solution.search(new int[]{5, 7, 7, 8, 8, 10}, 8));
    AssertUtils.assertEqual(0, solution.search(new int[]{5, 7, 7, 8, 8, 10}, 6));
  }
}
