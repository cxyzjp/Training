package com.example.offer;

import com.example.common.TreeNode;
import com.example.utils.AssertUtils;

import java.util.*;

public abstract class Offer32LevelOrder {
  public abstract int[] levelOrder(TreeNode root);

  private static class Solution1 extends Offer32LevelOrder {
    public int[] levelOrder(TreeNode root) {
      List<Integer> list = new ArrayList<>();
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);
      while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        if (node != null) {
          list.add(node.val);
          if (node.left != null) {
            queue.add(node.left);
          }
          if (node.right != null) {
            queue.add(node.right);
          }
        }
      }
      int[] result = new int[list.size()];
      for (int i = 0; i < list.size(); i++) {
        result[i] = list.get(i);
      }
      return result;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer32LevelOrder solution) {
    int[] result = solution.levelOrder(TreeNode.build(new Integer[]{3, 9, 20, null, null, 15, 7}));
    AssertUtils.assertEqual(Arrays.toString(new int[]{3, 9, 20, 15, 7}), Arrays.toString(result));
  }
}
