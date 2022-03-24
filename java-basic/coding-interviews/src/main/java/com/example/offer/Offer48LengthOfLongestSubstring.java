package com.example.offer;

import com.example.utils.AssertUtils;

import java.util.*;

public abstract class Offer48LengthOfLongestSubstring {
  public abstract int lengthOfLongestSubstring(String s);

  private static class Solution1 extends Offer48LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
      if (s == null || s.isEmpty()) {
        return 0;
      }
      char[] chars = s.toCharArray();
      Queue<Character> queue = new LinkedList<>();
      int max = 1;
      for (char cc : chars) {
        if (queue.contains(cc)) {
          while (!queue.isEmpty()) {
            if (queue.poll() == cc) {
              break;
            }
          }
        }
        queue.add(cc);
        max = Math.max(max, queue.size());
      }
      return max;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer48LengthOfLongestSubstring solution) {
    AssertUtils.assertEqual(5, solution.lengthOfLongestSubstring("tmmzuxt"));
    AssertUtils.assertEqual(3, solution.lengthOfLongestSubstring("abcabcbb"));
    AssertUtils.assertEqual(1, solution.lengthOfLongestSubstring("bbbbb"));
    AssertUtils.assertEqual(3, solution.lengthOfLongestSubstring("pwwkew"));
  }
}
