package com.example.offer;

import com.example.common.TreeNode;
import com.example.utils.AssertUtils;

import java.util.*;

public abstract class Offer32LevelOrder3 {
  public abstract List<List<Integer>> levelOrder(TreeNode root);

  private static class Solution1 extends Offer32LevelOrder3 {
    public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> lists = new ArrayList<>();
      Queue<TreeNode> queue = new LinkedList<>();
      if (root != null) {
        queue.add(root);
      }
      int flag = 0;
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
          if (flag == 0) {
            lists.add(temp);
          } else {
//            Collections.reverse(temp);
            List<Integer> temp1 = new ArrayList<>(temp.size());
            for (int i = temp.size() - 1; i >= 0; i--) {
              temp1.add(temp.get(i));
            }
            lists.add(temp1);
          }
        }
        flag = flag ^ 1;
      }
      return lists;
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer32LevelOrder3 solution) {
    List<List<Integer>> lists = solution.levelOrder(TreeNode.build(new Integer[]{3, 9, 20, null, null, 15, 7}));
    AssertUtils.assertEqual("[[3], [20, 9], [15, 7]]", lists.toString());
  }
}
