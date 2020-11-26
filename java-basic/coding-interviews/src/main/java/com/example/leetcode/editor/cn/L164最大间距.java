package com.example.leetcode.editor.cn;

//给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。 
//
// 如果数组元素个数小于 2，则返回 0。 
//
// 示例 1: 
//
// 输入: [3,6,9,1]
//输出: 3
//解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。 
//
// 示例 2: 
//
// 输入: [10]
//输出: 0
//解释: 数组元素个数小于 2，因此返回 0。 
//
// 说明: 
//
// 
// 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。 
// 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。 
// 
// Related Topics 排序 
// 👍 251 👎 0

public class L164最大间距 {
  public static void main(String[] args) {
    Solution solution = new L164最大间距().new Solution();

    System.out.println(solution.maximumGap(new int[]{3, 6, 9, 1, 20}));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maximumGap(int[] nums) {
      if (nums.length < 2) {
        return 0;
      }
      return m1(nums);
    }

    private int m1(int[] nums) {
      int max = 0;
      for (int i = 0; i < nums.length - 1; i++) {
        for (int j = i + 1; j < nums.length; j++) {
          if (nums[j] < nums[i]) {
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
          }
        }
        if (i > 1) {
          max = Math.max(max, nums[i] - nums[i - 1]);
        }
      }
      max = Math.max(max, nums[nums.length -1] - nums[nums.length - 2]);
      return max;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}