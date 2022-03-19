package com.example.offer;

import com.example.utils.AssertUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Offer50FirstUniqChar {
  public abstract char firstUniqChar(String s);

  private static class Solution1 extends Offer50FirstUniqChar {
    public char firstUniqChar(String s) {
      if (s == null || s.length() == 0) {
        return ' ';
      }
      char[] chars = s.toCharArray();
      Map<Character, Integer> map = new LinkedHashMap<>();
      for (char c : chars) {
        Integer count = map.get(c);
        if (count == null) {
          map.put(c, 1);
        } else if (count == 1) {
          map.put(c, 2);
        }
      }

      for (Map.Entry<Character, Integer> entry : map.entrySet()) {
        if (entry.getValue().equals(1)) {
          return entry.getKey();
        }
      }
      return ' ';
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer50FirstUniqChar solution) {
    AssertUtils.assertEqual('l', solution.firstUniqChar("leetcode"));
    AssertUtils.assertEqual('b', solution.firstUniqChar("abaccdeff"));
    AssertUtils.assertEqual(' ', solution.firstUniqChar(""));
  }
}
