package com.example.offer;

import java.util.Arrays;

public abstract class Offer51TwoSum {
  public abstract int[] twoSum(int[] nums, int target);

  private static class Solution1 extends Offer51TwoSum {
    public int[] twoSum(int[] nums, int target) {
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] > target) {
          break;
        }
        for (int j = i; j < nums.length; j++) {
          if (nums[j] > target) {
            break;
          }
          if (nums[i] + nums[j] == target) {
            return new int[]{nums[i], nums[j]};
          }
        }
      }
      return null;
    }
  }

  private static class Solution2 extends Offer51TwoSum {
    public int[] twoSum(int[] nums, int target) {
      int i = 0, j = nums.length - 1;
      while (i < j) {
        if (nums[j] > target) {
          j--;
          continue;
        }
        if (nums[i] + nums[j] == target) {
          return new int[]{nums[i], nums[j]};
        } else if (nums[i] + nums[j] < target) {
          i++;
        } else {
          j--;
        }
      }
      return null;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer51TwoSum solution) {
    // [2,7]
    System.out.println(Arrays.toString(solution.twoSum(new int[]{2, 7, 11, 15}, 9)));
    // [10,30]
    System.out.println(Arrays.toString(solution.twoSum(new int[]{10, 26, 30, 31, 47, 60}, 40)));
  }
}
