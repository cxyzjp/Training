package com.example.offer;

import com.example.utils.AssertUtils;

public abstract class Offer04FindNumberIn2DArray {
  public abstract boolean findNumberIn2DArray(int[][] matrix, int target);

  static class Solution1 extends Offer04FindNumberIn2DArray {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
      if (matrix.length == 0) {
        return false;
      }
      int colMax = matrix[0].length - 1;
      for (int[] row : matrix) {
        for (int j = 0; j <= colMax; j++) {
          if (row[j] > target) {
            colMax = j - 1;
            break;
          } else if (row[j] == target) {
            return true;
          }
        }
        if (colMax < 0) {
          return false;
        }
      }
      return false;
    }
  }

  static class Solution2 extends Offer04FindNumberIn2DArray {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
      }
      int rows = matrix.length;
      int cols = matrix[0].length;
      int row = 0;
      int col = cols - 1;
      while (row <= rows - 1 && col >= 0) {
        int v = matrix[row][col];
        if (v == target) {
          return true;
        } else if (v < target) {
          row++;
        } else {
          col--;
        }
      }
      return false;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer04FindNumberIn2DArray solution) {
    int[][] arr1 = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
    AssertUtils.assertEqual(true, solution.findNumberIn2DArray(arr1, 5));
    AssertUtils.assertEqual(true, solution.findNumberIn2DArray(arr1, 8));
    AssertUtils.assertEqual(false, solution.findNumberIn2DArray(arr1, 25));
    AssertUtils.assertEqual(true, solution.findNumberIn2DArray(new int[][]{{-5}}, -5));
  }
}
