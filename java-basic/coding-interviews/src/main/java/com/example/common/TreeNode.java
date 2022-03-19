package com.example.common;

import java.util.*;

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
        ", left=" + (left == null ? null : left.val) +
        ", right=" + (right == null ? null : right.val) +
        '}';
  }

  public String toArrayStr() {
    List<Integer> list = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(this);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node != null) {
        list.add(node.val);
        if (node.left == null && node.right == null) {
          continue;
        }
        queue.add(node.left);
        queue.add(node.right);
      } else {
        list.add(null);
      }
    }
    return Arrays.toString(list.toArray());
  }

  public static TreeNode build(Integer[] nums) {
    List<TreeNode> list = new ArrayList<>();
    TreeNode root = new TreeNode(nums[0]);
    list.add(root);
    for (int i = 1; i < nums.length; i++) {
      TreeNode node = nums[i] == null ? null : new TreeNode(nums[i]);
      list.add(node);
      int parentIndex = (i - 1) / 2;
      boolean isLeft = (i - 1) % 2 == 0;
      TreeNode parentNode = list.get(parentIndex);
      if (isLeft) {
        parentNode.left = node;
      } else {
        parentNode.right = node;
      }
    }
    return root;
  }
}
