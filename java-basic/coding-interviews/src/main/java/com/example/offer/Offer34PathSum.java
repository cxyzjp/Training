package com.example.offer;

import com.example.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Offer34PathSum {
  public abstract List<List<Integer>> pathSum(TreeNode root, int target);

  private static class Solution1 extends Offer34PathSum {
    LinkedList<Integer> list;
    List<List<Integer>> res;

    public List<List<Integer>> pathSum(TreeNode root, int target) {
      list = new LinkedList<>();
      res = new ArrayList<>();
      recur(root, target);
      return res;
    }

    public void recur(TreeNode root, int sum) {
      if (root == null) {
        return;
      }
      list.add(root.val);
      sum -= root.val;
      if (sum == 0 && root.left == null && root.right == null) {
        res.add(new ArrayList<>(list));
      }
      recur(root.left, sum);
      recur(root.right, sum);
      list.removeLast();
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer34PathSum solution) {
    // [[5,4,11,2],[5,8,4,5]]
    System.out.println(solution.pathSum(TreeNode.build(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, null, 5, 1}), 22));
    // []
    System.out.println(solution.pathSum(TreeNode.build(new Integer[]{1, 2, 3}), 5));
    // []
    System.out.println(solution.pathSum(TreeNode.build(new Integer[]{1, 2}), 0));
  }
}
