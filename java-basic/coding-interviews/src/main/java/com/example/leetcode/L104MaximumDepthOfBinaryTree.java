package com.example.leetcode;

import com.example.common.TreeNode;

import java.util.LinkedList;

/**
 * <pre>
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 * </pre>
 *
 * @author zhangjianping
 * @date 2020/11/19
 **/
public class L104MaximumDepthOfBinaryTree {

  public static void main(String[] args) {
    TreeNode t112 = new TreeNode(3);
    TreeNode t122 = new TreeNode(3);
    TreeNode t11 = new TreeNode(2, null, t112);
    TreeNode t12 = new TreeNode(2, null, t122);
    TreeNode t1 = new TreeNode(1, t11, t12);

    L104MaximumDepthOfBinaryTree l = new L104MaximumDepthOfBinaryTree();
    System.out.println(l.maxDepth(t1));
    System.out.println(l.maxDepth(t12));
    System.out.println(l.maxDepth(t122));
  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return m3(root);
  }

  /**
   * 递归
   */
  public int m1(TreeNode root) {
    int a = 0;
    if (root.left != null) {
      a = m1(root.left);
    }
    if (root.right != null) {
      int b = m1(root.right);
      if (b > a) {
        a = b;
      }
    }
    return a + 1;
  }

  /**
   * 递归
   */
  public int m2(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int max = Math.max(m2(root.left), m2(root.right));
    return max + 1;
  }

  /**
   * 广度优先搜索
   */
  public int m3(TreeNode root) {
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.push(root);
    int level = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size > 0) {
        TreeNode first = queue.poll();
        if (first != null && first.left != null) {
          queue.offer(first.left);
        }
        if (first != null && first.right != null) {
          queue.offer(first.right);
        }
        size--;
      }
      level++;
    }
    return level;
  }

}
