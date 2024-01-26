package com.leetcode.easy.bfsdfs;

import com.util.TreeNode;

/**
 * 226. Invert Binary Tree
 *
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 *       4              4
 *     /   \          /   \
 *    2     7   ->   7     2
 *   / \   / \      / \   / \
 *  1   3 6   9    9   6 3   1
 */
public class InvertBinaryTree {

    public static void main(String[] args) {
        // Creating the binary tree
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        TreeNode result = invertTree(root);

        System.out.println(result);
    }

    // DFS
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
