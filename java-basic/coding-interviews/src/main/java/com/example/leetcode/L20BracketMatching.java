package com.example.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * @author zhangjianping
 * @date 2020/11/17
 **/
public class L20BracketMatching {

  public static void main(String[] args) {
    L20BracketMatching l = new L20BracketMatching();

    String s = "{[]}";
    boolean valid = l.isValid(s);
    System.out.println(s + ", valid: " + valid);
  }

  public boolean isValid(String s) {
    if (null == s || s.isEmpty()) {
      return true;
    }

    Map<Character, Character> map = new HashMap<>();
    map.put(')', '(');
    map.put('}', '{');
    map.put(']', '[');

    LinkedList<Character> list = new LinkedList<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        if (list.isEmpty() || !map.get(c).equals(list.pop())) {
          return false;
        }
      } else {
        list.push(c);
      }
    }
    return list.isEmpty();
  }
}
