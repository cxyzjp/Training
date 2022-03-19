package com.example.offer;

import com.example.common.TreeNode;
import com.example.utils.AssertUtils;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Offer26IsSubStructure {
  public abstract boolean isSubStructure(TreeNode A, TreeNode B);

  private static class Solution1 extends Offer26IsSubStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
      if (B == null || A == null) {
        return false;
      }
      int bRootVal = B.val;
      Queue<TreeNode> aQ = new LinkedList<>();
      aQ.add(A);
      while (!aQ.isEmpty()) {
        TreeNode a = aQ.poll();
        if (a.val == bRootVal) {
          if (isSame(a, B)) {
            return true;
          }
        }
        if (a.left != null) {
          aQ.add(a.left);
        }
        if (a.right != null) {
          aQ.add(a.right);
        }
      }
      return false;
    }

    private boolean isSame(TreeNode A, TreeNode B) {
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(B);
      queue.add(A);
      while (!queue.isEmpty()) {
        TreeNode b = queue.poll();
        TreeNode a = queue.poll();
        if (a == null || a.val != b.val) {
          return false;
        }
        if (b.left != null) {
          if (a.left == null) {
            return false;
          }
          queue.add(b.left);
          queue.add(a.left);
        }
        if (b.right != null) {
          if (a.right == null) {
            return false;
          }
          queue.add(b.right);
          queue.add(a.right);
        }
      }
      return true;
    }
  }

  private static class Solution2 extends Offer26IsSubStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
      if (A == null || B == null) {
        return false;
      }
      return isSame(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSame(TreeNode A, TreeNode B) {
      if (B == null) {
        return true; // 表示B的某个节点已经遍历完
      }
      if (A == null || A.val != B.val) {
        return false;
      }
      return isSame(A.left, B.left) && isSame(A.right, B.right);
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer26IsSubStructure solution) {
    TreeNode A = TreeNode.build(new Integer[]{1, 2, 3});
    TreeNode B = TreeNode.build(new Integer[]{3, 1});
    TreeNode A1 = TreeNode.build(new Integer[]{3, 4, 5, 1, 2});
    TreeNode B1 = TreeNode.build(new Integer[]{4, 1});
    AssertUtils.assertEqual(false, solution.isSubStructure(A, B));
    AssertUtils.assertEqual(true, solution.isSubStructure(A1, B1));
  }
}
