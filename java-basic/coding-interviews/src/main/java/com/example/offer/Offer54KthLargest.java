package com.example.offer;

import com.example.common.TreeNode;
import com.example.utils.AssertUtils;

import java.util.Stack;

public abstract class Offer54KthLargest {
  public abstract int kthLargest(TreeNode root, int k);

  private static class Solution1 extends Offer54KthLargest {
    Stack<Integer> stack = new Stack<>();

    public int kthLargest(TreeNode root, int k) {
      if (root == null) {
        return 0;
      }
      tree(root);
      while (!stack.isEmpty()) {
        Integer pop = stack.pop();
        k--;
        if (k == 0) {
          return pop;
        }
      }
      return 0;
    }

    private void tree(TreeNode node) {
      if (node == null) {
        return;
      }
      tree(node.left);
      stack.add(node.val);
      tree(node.right);
    }
  }

  private static class Solution2 extends Offer54KthLargest {
    Integer res, k;

    public int kthLargest(TreeNode root, int k) {
      this.k = k;
      res = 0;
      tree(root);
      return res;
    }

    private void tree(TreeNode node) {
      if (node == null) {
        return;
      }
      tree(node.right);
      if (k == 0) {
        return;
      }
      k = k - 1;
      if (k == 0) {
        res = node.val;
      }
      tree(node.left);
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer54KthLargest solution) {
    AssertUtils.assertEqual(4, solution.kthLargest(TreeNode.build(new Integer[]{3, 1, 4, null, 2, null, null}), 1));
    AssertUtils.assertEqual(4, solution.kthLargest(TreeNode.build(new Integer[]{5, 3, 6, 2, 4, null, null, 1}), 3));
  }
}
