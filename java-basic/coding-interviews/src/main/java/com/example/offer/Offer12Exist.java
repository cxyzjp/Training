package com.example.offer;

import com.example.utils.AssertUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class Offer12Exist {
  public abstract boolean exist(char[][] board, String word);

  private static class Solution1 extends Offer12Exist {
    List<String> list = new ArrayList<>();

    public boolean exist(char[][] board, String word) {
      char[] chars = word.toCharArray();
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          list.clear();
          boolean ex = find(board, chars, i, j, 0);
          if (ex) {
            return true;
          }
        }
      }
      return false;
    }

    private boolean find(char[][] board, char[] chars, int i, int j, int k) {
      list.add(i + "-" + j);
      if (k >= chars.length) {
        return true;
      }
      if (i >= board.length || i < 0 || j >= board[0].length || j < 0) {
        return false;
      }
      if (board[i][j] == chars[k]) {
        boolean up;
        if (!list.contains((i - 1) + "-" + j)) {
          up = find(board, chars, i - 1, j, k + 1);
          if (!up) {
            if (!list.isEmpty()) {
              list.remove(list.size() - 1);
            }
          } else {
            return true;
          }
        }
        boolean right;
        if (!list.contains(i + "-" + (j + 1))) {
          right = find(board, chars, i, j + 1, k + 1);
          if (!right) {
            if (!list.isEmpty()) {
              list.remove(list.size() - 1);
            }
          } else {
            return true;
          }
        }
        boolean down;
        if (!list.contains((i + 1) + "-" + j)) {
          down = find(board, chars, i + 1, j, k + 1);
          if (!down) {
            if (!list.isEmpty()) {
              list.remove(list.size() - 1);
            }
          } else {
            return true;
          }
        }
        boolean left;
        if (!list.contains(i + "-" + (j - 1))) {
          left = find(board, chars, i, j - 1, k + 1);
          if (!left) {
            if (!list.isEmpty()) {
              list.remove(list.size() - 1);
            }
          } else {
            return true;
          }
        }
        return false;
      }
      return false;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer12Exist solution) {
    AssertUtils.assertEqual(false, solution.exist(new char[][]{{'A', 'A'}}, "AAA"));
    AssertUtils.assertEqual(true, solution.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCEFSADEESE"));
    AssertUtils.assertEqual(true, solution.exist(new char[][]{{'A'}}, "A"));
    AssertUtils.assertEqual(true, solution.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED"));
    AssertUtils.assertEqual(false, solution.exist(new char[][]{{'a', 'b'}, {'c', 'd'}}, "abcd"));
  }
}
