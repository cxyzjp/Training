package com.example.offer;

import com.example.utils.AssertUtils;

import java.util.Stack;

public abstract class Offer58ReverseWords {
  public abstract String reverseWords(String s);

  private static class Solution1 extends Offer58ReverseWords {
    public String reverseWords(String s) {
      if (s == null || s.isEmpty()) {
        return "";
      }
      String[] arr = s.split(" ");
      Stack<String> stack = new Stack<>();
      for (String s1 : arr) {
        if (!s1.isEmpty() && !s1.equals(" ")) {
          stack.push(s1);
          stack.push(" ");
        }
      }
      if (stack.isEmpty()) {
        return "";
      }
      stack.pop();
      StringBuilder sb = new StringBuilder();
      while (!stack.isEmpty()) {
        sb.append(stack.pop());
      }
      return sb.toString();
    }
  }

  private static class Solution2 extends Offer58ReverseWords {
    public String reverseWords(String s) {
      String[] arr = s.trim().split(" ");
      StringBuilder sb = new StringBuilder();
      for (int i = arr.length - 1; i >= 0; i--) {
        if (arr[i].isEmpty()) {
          continue;
        }
        sb.append(arr[i]).append(" ");
      }
      return sb.toString().trim();
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer58ReverseWords solution) {
    AssertUtils.assertEqual("blue is sky the", solution.reverseWords("the sky is blue"));
    AssertUtils.assertEqual("world! hello", solution.reverseWords("  hello world!  "));
    AssertUtils.assertEqual("example good a", solution.reverseWords("a good   example"));
  }
}
