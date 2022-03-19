package com.example.offer;

import com.example.common.TreeNode;
import com.example.utils.AssertUtils;

import java.util.*;

public abstract class Offer32LevelOrder2 {
  public abstract List<List<Integer>> levelOrder(TreeNode root);

  private static class Solution1 extends Offer32LevelOrder2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> lists = new ArrayList<>();
      if (root == null) {
        return lists;
      }
      Queue<TreeNode> q1 = new LinkedList<>();
      Queue<TreeNode> q2 = new LinkedList<>();
      Queue<TreeNode>[] qs = new Queue[]{q1, q2};

      q1.add(root);
      int i = 0;
      Queue<TreeNode> crr = qs[0];
      while (crr.size() > 0) {
        int nextIndex = i ^ 1;
        Queue<TreeNode> next = qs[nextIndex];
        List<Integer> list = new ArrayList<>();
        while (!crr.isEmpty()) {
          TreeNode node = crr.poll();
          if (node != null) {
            list.add(node.val);
            if (node.left != null) {
              next.add(node.left);
            }
            if (node.right != null) {
              next.add(node.right);
            }
          }
        }
        if (!list.isEmpty()) {
          lists.add(list);
        }
        crr = next;
        i = nextIndex;
      }
      return lists;
    }
  }

  private static class Solution2 extends Offer32LevelOrder2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> lists = new ArrayList<>();
      Queue<TreeNode> queue = new LinkedList<>();
      if (root != null) {
        queue.add(root);
      }
      while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> temp = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
          TreeNode node = queue.poll();
          if (node != null) {
            temp.add(node.val);
            if (node.left != null) {
              queue.add(node.left);
            }
            if (node.right != null) {
              queue.add(node.right);
            }
          }
        }
        if (!temp.isEmpty()) {
          lists.add(temp);
        }
      }
      return lists;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer32LevelOrder2 solution) {
    List<List<Integer>> lists = solution.levelOrder(TreeNode.build(new Integer[]{3, 9, 20, null, null, 15, 7}));
    AssertUtils.assertEqual("[[3], [9, 20], [15, 7]]", lists.toString());
  }
}
