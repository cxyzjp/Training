package com.example.offer;

import java.util.HashMap;
import java.util.Map;

public abstract class Offer35CopyRandomList {
  static class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  public abstract Node copyRandomList(Node head);

  private static class Solution1 extends Offer35CopyRandomList {
    public Node copyRandomList(Node head) {
      Node crr = head;

      Map<Node, Node> oldNewMap = new HashMap<>();
      while (crr != null) {
        Node newCrr = new Node(crr.val);
        oldNewMap.put(crr, newCrr);
        crr = crr.next;
      }

      crr = head;
      while (crr != null) {
        Node node = oldNewMap.get(crr);
        node.next = crr.next == null ? null : oldNewMap.get(crr.next);
        node.random = crr.random == null ? null : oldNewMap.get(crr.random);
        crr = crr.next;
      }
      return oldNewMap.get(head);
    }
  }

  public static void main(String[] args) {
    test(new Solution1());
  }

  private static void test(Offer35CopyRandomList solution) {
//    [[3,null],[3,0],[3,null]]
    Node n1 = new Node(3);
    Node n2 = new Node(3);
    Node n3 = new Node(3);

  }
}
