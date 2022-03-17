package com.example.offer;

import com.example.utils.AssertUtils;

import java.util.HashSet;
import java.util.Set;

public abstract class Offer03FindRepeatNumber {
  public abstract int findRepeatNumber(int[] nums);

  private static class Solution1 extends Offer03FindRepeatNumber {
    public int findRepeatNumber(int[] nums) {
      Set<Integer> repeat = new HashSet<>();
      for (int num : nums) {
        if (!repeat.add(num)) {
          return num;
        }
      }
      return -1;
    }
  }

  private static class Solution2 extends Offer03FindRepeatNumber {
    public int findRepeatNumber(int[] nums) {
      int i = 0;
      while (i < nums.length) {
        if (nums[i] == i) {
          // 索引=值
          i++;
        } else if (nums[i] == nums[nums[i]]) {
          // 当前值=要交换的索引的值，则重复
          return nums[i];
        } else {
          int temp = nums[i];
          nums[i] = nums[temp];
          nums[temp] = temp;
        }
      }
      return -1;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer03FindRepeatNumber solution) {
    AssertUtils.assertEqual(2, solution.findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    AssertUtils.assertEqual(0, solution.findRepeatNumber(new int[]{3, 4, 2, 0, 0, 1}));
  }
}
