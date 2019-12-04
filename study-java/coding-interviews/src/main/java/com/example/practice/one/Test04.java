package com.example.practice.one;

import java.util.Arrays;

/**
 * description:
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * <p>
 * author: bowen
 * date: 2019/6/18
 */
public class Test04 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Test04 t = new Test04();
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        TreeNode node = t.reConstructBinaryTree(pre,in);
        t.pre(node);
        System.out.println();
        t.in(node);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode node = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                // 左节点的前序和中序
                node.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                // 右节点的前序和中序
                node.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i+1, pre.length), Arrays.copyOfRange(in, i+1, in.length));
                break;
            }
        }
        return node;
    }

    public void pre(TreeNode node){
        System.out.print(node.val + " ");
        if(node.left != null){
            pre(node.left);
        }
        if(node.right !=null){
            pre(node.right);
        }
    }

    public void in(TreeNode node){
        if(node.left != null){
            in(node.left);
        }
        System.out.print(node.val + " ");
        if(node.right !=null){
            in(node.right);
        }
    }
}
