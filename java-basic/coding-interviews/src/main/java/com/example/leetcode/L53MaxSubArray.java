package com.example.leetcode;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * @author zhangjianping
 * @date 2020/11/16
 **/
public class L53MaxSubArray {
  public static void main(String[] args) {
    L53MaxSubArray l = new L53MaxSubArray();

    int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(l.maxSubArray(nums));
  }

  public int maxSubArray(int[] nums) {
    int max = nums[0];
    int start = 0;
    int end = 0;
    for (int i = 0; i < nums.length; i++) {
      int sum = 0;
      for (int j = i; j < nums.length; j++) {
        sum += nums[j];
        if (sum > max) {
          max = sum;
          start = i;
          end = j;
        }
      }
    }

    System.out.println(Arrays.toString(Arrays.copyOfRange(nums, start, end + 1)));
    return max;
  }
}
