package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer58ReverseLeftWords {
  public abstract String reverseLeftWords(String s, int n);

  private static class Solution1 extends Offer58ReverseLeftWords {
    public String reverseLeftWords(String s, int n) {
      if (n == 0 || n == s.length()) {
        return s;
      }
      String pre = s.substring(0, n);
      String tail = s.substring(n);
      return tail + pre;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer58ReverseLeftWords solution) {
    AssertUtils.assertEqual("cdefgab", solution.reverseLeftWords("abcdefg", 2));
    AssertUtils.assertEqual("abcdefg", solution.reverseLeftWords("abcdefg", 0));
    AssertUtils.assertEqual("abcdefg", solution.reverseLeftWords("abcdefg", 7));
    AssertUtils.assertEqual("umghlrlose", solution.reverseLeftWords("lrloseumgh", 6));
  }
}
