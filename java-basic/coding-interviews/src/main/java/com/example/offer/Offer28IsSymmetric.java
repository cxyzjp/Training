package com.example.offer;

import com.example.common.TreeNode;
import com.example.utils.AssertUtils;

public abstract class Offer28IsSymmetric {
  public abstract boolean isSymmetric(TreeNode root);

  private static class Solution1 extends Offer28IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
      if (root == null) {
        return true;
      }
      return isSym(root.left, root.right);
    }

    private boolean isSym(TreeNode left, TreeNode right) {
      if (left == null && right == null) {
        return true;
      }
      if (left == null || right == null || left.val != right.val) {
        return false;
      }
      return isSym(left.left, right.right) && isSym(left.right, right.left);
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer28IsSymmetric solution) {
    AssertUtils.assertEqual(true, solution.isSymmetric(TreeNode.build(new Integer[]{1, 2, 2, 3, 4, 4, 3})));
    AssertUtils.assertEqual(false, solution.isSymmetric(TreeNode.build(new Integer[]{1, 2, 2, null, 3, null, 3})));
  }
}
