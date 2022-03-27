package com.example.offer;

import com.example.common.Node;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Offer36TreeToDoublyList {
  public abstract Node treeToDoublyList(Node root);

  private static class Solution1 extends Offer36TreeToDoublyList {
    Queue<Node> queue = new LinkedList<>();

    public Node treeToDoublyList(Node root) {
      if (root == null) {
        return null;
      }
      queue = new LinkedList<>();
      tree(root);
      Node head = new Node();
      head.right = queue.poll();
      if (head.right == null) {
        return head;
      }
      Node pre = head.right;
      Node crr = null;
      while (!queue.isEmpty()) {
        crr = queue.poll();
        pre.right = crr;
        crr.left = pre;
        pre = crr;
      }
      if (crr == null) {
        head.right.left = head.right;
        head.right.right = head.right;
      } else {
        head.right.left = crr;
        crr.right = head.right;
      }
      return head.right;
    }

    private void tree(Node root) {
      if (root == null) {
        return;
      }
      if (root.left != null) {
        tree(root.left);
      }
      queue.add(root);
      if (root.right != null) {
        tree(root.right);
      }
    }
  }

  private static class Solution2 extends Offer36TreeToDoublyList {
    Node head, pre;

    public Node treeToDoublyList(Node root) {
      if (root == null) {
        return null;
      }
      tree(root);
      head.left = pre;
      pre.right = head;
      return head;
    }

    private void tree(Node crr) {
      if (crr == null) {
        return;
      }
      tree(crr.left);
      if (pre == null) {
        head = crr;
      } else {
        pre.right = crr;
      }
      crr.left = pre;
      pre = crr;
      tree(crr.right);
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
    test(new Solution2());
  }

  private static void test(Offer36TreeToDoublyList solution) {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    n2.left = n1;
    n2.right = n3;
    n4.left = n2;
    n4.right = n5;

    Node node = solution.treeToDoublyList(n4);
    System.out.println(node);
  }
}
