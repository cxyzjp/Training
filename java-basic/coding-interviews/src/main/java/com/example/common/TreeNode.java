package com.example.common;

/**
 * TreeNode
 *
 * @author zhangjianping
 * @date 2020/11/18
 **/
public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(int val) {
    this.val = val;
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    return "TreeNode{" +
            "val=" + val +
            ", left=" + left.val +
            ", right=" + right.val +
            '}';
  }
}
