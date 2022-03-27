package com.example.offer;

import java.util.Arrays;

public abstract class Offer21Exchange {
  public abstract int[] exchange(int[] nums);

  private static class Solution1 extends Offer21Exchange {
    public int[] exchange(int[] nums) {
      int i = 0, j = 0;
      while (i < nums.length && j < nums.length) {
        while (i < nums.length) {
          if (nums[i] % 2 == 0) {
            break;
          } else {
            i++;
          }
        }
        if (j < i) {
          j = i + 1;
        }
        while (j < nums.length) {
          if (nums[j] % 2 != 0) {
            break;
          } else {
            j++;
          }
        }
        if (i < nums.length && j < nums.length) {
          int temp = nums[i];
          nums[i] = nums[j];
          nums[j] = temp;
          i++;
          j++;
        }
      }
      return nums;
    }
  }

  private static class Solution2 extends Offer21Exchange {
    public int[] exchange(int[] nums) {
      int i = 0, j = nums.length - 1;
      while (i < j) {
        while (i < j) {
          if ((nums[i] & 1) == 0) {
            break;
          } else {
            i++;
          }
        }
        while (j > i) {
          if ((nums[j] & 1) == 1) {
            break;
          } else {
            j--;
          }
        }
        if (i < j) {
          int temp = nums[i];
          nums[i] = nums[j];
          nums[j] = temp;
          i++;
          j--;
        }
      }
      return nums;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer21Exchange solution) {
    // 1,3,2,4    3,1,2,4
    System.out.println(Arrays.toString(solution.exchange(new int[]{1, 2, 3, 4})));
  }
}
