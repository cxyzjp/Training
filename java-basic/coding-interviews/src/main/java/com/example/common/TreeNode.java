package com.example.common;

/**
 * TreeNode
 *
 * @author zhangjianping
 * @date 2020/11/18
 **/
public class TreeNode<T> {
  public T val;
  public TreeNode<T> left;
  public TreeNode<T> right;

  TreeNode(T x) {
    val = x;
  }
}
