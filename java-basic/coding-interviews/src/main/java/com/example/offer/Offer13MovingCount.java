package com.example.offer;

import com.example.utils.AssertUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public abstract class Offer13MovingCount {
  public abstract int movingCount(int m, int n, int k);

  private static class Solution1 extends Offer13MovingCount {
    Set<String> sets;

    public int movingCount(int m, int n, int k) {
      sets = new HashSet<>();
      moving(m, n, 0, 0, k);
      return sets.size();
    }

    private void moving(int m, int n, int i, int j, int k) {
      if (i < 0 || i > m - 1 || j < 0 || j > n - 1) {
        return;
      }
      int temp = i / 100 + (i / 10 % 10) + (i % 10);
      temp += j / 100 + (j / 10 % 10) + (j % 10);
      if (temp > k) {
        return;
      }
      sets.add(i + "-" + j);
//      if (!sets.contains((i - 1) + "-" + j)) {
//        moving(m, n, i - 1, j, k);
//      }
      if (!sets.contains(i + "-" + (j + 1))) {
        moving(m, n, i, j + 1, k);
      }
      if (!sets.contains((i + 1) + "-" + j)) {
        moving(m, n, i + 1, j, k);
      }
//      if (!sets.contains(j + "-" + (j - 1))) {
//        moving(m, n, i, j - 1, k);
//      }
    }
  }

  private static class Solution2 extends Offer13MovingCount {
    public int movingCount(int m, int n, int k) {
      int res = 0;
      boolean[][] ex = new boolean[m][n];
      Queue<int[]> queue = new LinkedList<>();
      queue.add(new int[]{0, 0});
      while (!queue.isEmpty()) {
        int[] poll = queue.poll();
        if (ex[poll[0]][poll[1]]) {
          continue;
        }
        ex[poll[0]][poll[1]] = true;
        int temp = poll[0] / 100 + (poll[0] / 10 % 10) + (poll[0] % 10);
        temp += poll[1] / 100 + (poll[1] / 10 % 10) + (poll[1] % 10);
        if (temp > k) {
          continue;
        }
        res++;
        if (poll[0] + 1 < m) {
          queue.add(new int[]{poll[0] + 1, poll[1]});
        }
        if (poll[1] + 1 < n) {
          queue.add(new int[]{poll[0], poll[1] + 1});
        }
      }
      return res;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer13MovingCount solution) {
    AssertUtils.assertEqual(3, solution.movingCount(2, 3, 1));
    AssertUtils.assertEqual(1, solution.movingCount(3, 1, 0));
    AssertUtils.assertEqual(1, solution.movingCount(1, 1, 2));
    AssertUtils.assertEqual(6, solution.movingCount(3, 2, 9));
  }
}
