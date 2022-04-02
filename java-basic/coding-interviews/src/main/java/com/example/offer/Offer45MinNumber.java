package com.example.offer;

import com.example.utils.AssertUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class Offer45MinNumber {
  public abstract String minNumber(int[] nums);

  private static class Solution1 extends Offer45MinNumber {
    public String minNumber(int[] nums) {
      Map<Integer, char[]> nc = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        char[] chars = String.valueOf(nums[i]).toCharArray();
        nc.put(i, chars);
      }
      for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
          char[] ic = nc.get(i);
          char[] jc = nc.get(j);
          int minL = Math.min(ic.length, jc.length);
          int k = 0;
          int mi = -1;
          while (k < minL) {
            if (ic[k] > jc[k]) {
              mi = j;
              break;
            } else if (ic[k] < jc[k]) {
              mi = i;
              break;
            }
            k++;
          }
          if (mi < 0) {
            if (ic.length > jc.length) {
              if (ic[minL] == '0') {
                mi = i;
              } else {
                mi = j;
              }
            } else if (ic.length < jc.length) {
              if (jc[minL] == '0') {
                mi = j;
              } else {
                mi = i;
              }
            }
          }
          if (mi == j) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            nc.put(i, jc);
            nc.put(j, ic);
          }
        }
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < nums.length; i++) {
        char[] chars = nc.get(i);
        sb.append(chars);
      }
      return sb.toString();
    }
  }

  /**
   * ab > ba 则 b<a
   */
  private static class Solution2 extends Offer45MinNumber {
    public String minNumber(int[] nums) {
      for (int i = 0; i < nums.length - 1; i++) {
        for (int j = i + 1; j < nums.length; j++) {
          String ab = nums[i] + "" + nums[j];
          String ba = nums[j] + "" + nums[i];
          if (ab.compareTo(ba) > 0) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
          }
        }
      }
      StringBuilder sb = new StringBuilder();
      for (int num : nums) {
        sb.append(num);
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    test(new Solution2());
  }

  private static void test(Offer45MinNumber solution) {
    AssertUtils.assertEqual("102", solution.minNumber(new int[]{10, 2}));
    AssertUtils.assertEqual("3033459", solution.minNumber(new int[]{3, 30, 34, 5, 9}));
    AssertUtils.assertEqual("123321", solution.minNumber(new int[]{123, 321}));
  }
}
