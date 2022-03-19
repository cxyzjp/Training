package com.example.offer;

import com.example.common.TreeNode;
import com.example.utils.AssertUtils;

import java.util.*;

public abstract class Offer27MirrorTree {
  public abstract TreeNode mirrorTree(TreeNode root);

  private static class Solution1 extends Offer27MirrorTree {
    public TreeNode mirrorTree(TreeNode root) {
      if (root == null) {
        return null;
      }
      mirror(root);
      return root;
    }

    private void mirror(TreeNode node) {
      TreeNode temp = node.left;
      node.left = node.right;
      node.right = temp;
      if (node.left != null) {
        mirror(node.left);
      }
      if (node.right != null) {
        mirror(node.right);
      }
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer27MirrorTree solution) {
    TreeNode treeNode = solution.mirrorTree(TreeNode.build(new Integer[]{4, 2, 7, 1, 3, 6, 9}));
    AssertUtils.assertEqual(Arrays.toString(new int[]{4, 7, 2, 9, 6, 3, 1}), treeNode.toArrayStr());
  }
}
