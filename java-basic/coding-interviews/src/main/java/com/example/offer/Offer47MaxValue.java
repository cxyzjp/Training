package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer47MaxValue {
  public abstract int maxValue(int[][] grid);

  private static class Solution1 extends Offer47MaxValue {
    public int maxValue(int[][] grid) {
      if (grid == null || grid.length == 0) {
        return 0;
      }
      return max(grid, 0, 0);
    }

    private int max(int[][] grid, int row, int col) {
      int right = 0;
      int down = 0;
      if (col < grid[0].length - 1) {
        right = max(grid, row, col + 1);
      }
      if (row < grid.length - 1) {
        down = max(grid, row + 1, col);
      }
      return grid[row][col] + Math.max(right, down);
    }
  }

  private static class Solution2 extends Offer47MaxValue {
    public int maxValue(int[][] grid) {
      if (grid == null || grid.length == 0) {
        return 0;
      }
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          if (i == 0 && j > 0) {
            grid[i][j] = grid[i][j] + grid[i][j - 1];
          } else if (i > 0 && j == 0) {
            grid[i][j] = grid[i][j] + grid[i - 1][j];
          } else if (i > 0) {
            grid[i][j] = grid[i][j] + Math.max(grid[i - 1][j], grid[i][j - 1]);
          }
        }
      }
      return grid[grid.length - 1][grid[0].length - 1];
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer47MaxValue solution) {
    AssertUtils.assertEqual(12, solution.maxValue(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    AssertUtils.assertEqual(9, solution.maxValue(new int[][]{{1, 2, 5}, {3, 2, 1}}));
    AssertUtils.assertEqual(210, solution.maxValue(new int[][]{
        {3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3}, {0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2},
        {8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9}, {1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7},
        {8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8}, {4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9},
        {6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1}, {8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3},
        {9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3}, {0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8},
        {4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3}, {2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3},
        {0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3}, {0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5},
        {6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2}, {7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0},
        {3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0}, {5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7}}));
  }
}
