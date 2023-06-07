package com.util;

import com.leetcode.easy.bfsdfs.MergeTwoBinaryTrees;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public TreeNode setLeft(TreeNode node) {
        left = node;
        return this;
    }

    public TreeNode setRight(TreeNode node) {
        right = node;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(this, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null");
            return;
        }

        sb.append(node.val);

        if (node.left != null || node.right != null) {
            sb.append(" [");
            buildString(node.left, sb);
            sb.append(", ");
            buildString(node.right, sb);
            sb.append("]");
        }
    }
}
