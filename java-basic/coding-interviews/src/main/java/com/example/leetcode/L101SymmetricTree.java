package com.example.leetcode;

import java.util.LinkedList;

/**
 * <pre>
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * </pre>
 *
 * @author zhangjianping
 * @date 2020/11/18
 **/
public class L101SymmetricTree {

  public static void main(String[] args) {
    // false
    TreeNode t112 = new TreeNode(3);
    TreeNode t122 = new TreeNode(3);
    TreeNode t11 = new TreeNode(2, null, t112);
    TreeNode t12 = new TreeNode(2, null, t122);
    TreeNode t1 = new TreeNode(1, t11, t12);

    // true
    TreeNode p111 = new TreeNode(3);
    TreeNode p112 = new TreeNode(4);
    TreeNode p121 = new TreeNode(4);
    TreeNode p122 = new TreeNode(3);
    TreeNode p11 = new TreeNode(2, p111, p112);
    TreeNode p12 = new TreeNode(2, p121, p122);
    TreeNode p1 = new TreeNode(1, p11, p12);

    L101SymmetricTree l = new L101SymmetricTree();
//    System.out.println(l.isSymmetric(t1));

//    System.out.println(l.isSymmetric2(t1));

    System.out.println(l.isSymmetric4(p1));
    System.out.println(l.isSymmetric4(t1));
  }

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return m1(root.left, root.right);
  }

  public boolean isSymmetric2(TreeNode root) {
    LinkedList<TreeNode> nodes = new LinkedList<>();
    nodes.add(root);
    return m2(nodes);
  }

  public boolean isSymmetric3(TreeNode root) {
    if (root == null) {
      return true;
    }
    LinkedList<TreeNode> nodes = new LinkedList<>();
    nodes.add(root.left);
    nodes.add(root.right);
    return m3(nodes);
  }

  public boolean isSymmetric4(TreeNode root) {
    if (root == null) {
      return true;
    }
    LinkedList<TreeNode> nodes = new LinkedList<>();
    nodes.add(root.left);
    nodes.add(root.right);
    return m3(nodes);
  }

  /**
   * 递归
   */
  public boolean m1(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }
    if (p == null || q == null) {
      return false;
    }
    return p.val == q.val && m1(p.left, q.right) && m1(p.right, q.left);
  }

  /**
   * 一层一层的轮询
   */
  public boolean m2(LinkedList<TreeNode> nodes) {
    int size = nodes.size();
    if (size > 1) {
      for (int i = 0; i < size / 2; i++) {
        if (nodes.get(i) == null || nodes.get(size - i - 1) == null) {
          if (nodes.get(i) == null && nodes.get(size - i - 1) == null) {
            continue;
          } else {
            return false;
          }
        }
        if (nodes.get(i).val != nodes.get(size - i - 1).val) {
          return false;
        }
      }
    }
    LinkedList<TreeNode> newNodes = new LinkedList<>();
    for (TreeNode node : nodes) {
      if (node != null) {
        newNodes.add(node.left);
        newNodes.add(node.right);
      }
    }
    if (!newNodes.isEmpty()) {
      return m2(newNodes);
    }
    return true;
  }

  /**
   * 递归，链表
   */
  public boolean m3(LinkedList<TreeNode> nodes) {
    TreeNode p = nodes.poll();
    TreeNode q = nodes.poll();
    if (p == null && q == null) {
      return true;
    }
    if (p == null || q == null) {
      return false;
    }
    if (p.val != q.val) {
      return false;
    }
    nodes.push(p.left);
    nodes.push(q.right);
    nodes.push(p.right);
    nodes.push(q.left);
    return m3(nodes);
  }

  /**
   * 迭代
   */
  public boolean m4(LinkedList<TreeNode> nodes) {
    while (!nodes.isEmpty()) {
      TreeNode p = nodes.poll();
      TreeNode q = nodes.poll();
      if (p == null && q == null) {
        return true;
      }
      if (p == null || q == null) {
        return false;
      }
      if (p.val != q.val) {
        return false;
      }
      nodes.push(p.left);
      nodes.push(q.right);
      nodes.push(p.right);
      nodes.push(q.left);
    }
    return true;
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
