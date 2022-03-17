package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer05ReplaceSpace {
  public abstract String replaceSpace(String s);

  private static class Solution1 extends Offer05ReplaceSpace {
    public String replaceSpace(String s) {
      return s.replaceAll(" ", "%20");
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer05ReplaceSpace solution) {
    AssertUtils.assertEqual("We%20are%20happy.", solution.replaceSpace("We are happy."));
    AssertUtils.assertEqual("%20", solution.replaceSpace(" "));
    AssertUtils.assertEqual("%20%20", solution.replaceSpace("  "));
    AssertUtils.assertEqual("", solution.replaceSpace(""));
  }
}
