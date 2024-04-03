package com.leetcode.easy.bfsdfs;

import com.util.TreeNode;

/**
 * #110. Balanced Binary Tree
 *
 * Given a binary tree, determine if it is height-balanced.
 * (which subtrees differs in height no more than 1-level)
 * .
 * Example 1:
 *        3
 *      /   \
 *     9    20
 *         /  \
 *        15   7
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 *
 * Example 2:
 *             1
 *           /   \
 *          2     2
 *        /  \
 *       3    3
 *      / \
 *     4   4
 *
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 *
 * Example 3:
 *
 * Input: root = []
 * Output: true
 */
public class BalancedBinaryTree {

    public static void main(String[] args) {
        // Ex 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);

        root1.right.right = new TreeNode(7);
        root1.right.left = new TreeNode(15);

        System.out.println(isBalanced(root1)); // true

        TreeNode root2 = new TreeNode(1);

        // Ex 2
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);

        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(3);

        root2.left.left.left = new TreeNode(4);
        root2.left.left.right = new TreeNode(4);

        System.out.println(isBalanced(root2)); // false

        // Ex 3
        System.out.println(isBalanced(null)); // true
    }

    private final static int UNBALANCED = -1;

    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return dfs(root) != UNBALANCED;
    }

    // returns height of a node, or, if unbalanced, returns -1 (UNBALANCED constant)
    private static int dfs(TreeNode root) {
        // Base case - leaf node
        if (root.left == null && root.right == null) {
            return 1; // height of a leaf node
        }

        // Left subtree DFS
        int leftH = 0;
        if (root.left != null) {
            leftH = dfs(root.left);
        }
        if (leftH == UNBALANCED) return UNBALANCED;

        // Right subtree DFS
        int rightH = 0;
        if (root.right != null) {
            rightH = dfs(root.right);
        }
        if (rightH == UNBALANCED) return UNBALANCED;

        // Check current tree (if balanced - return height, for further calculations. if not return UNBALANCED)
        if (Math.abs(rightH - leftH) > 1) {
            return UNBALANCED;
        } else {
            return Math.max(rightH, leftH) + 1; // +1 - height of current subtree with current node considered
        }
    }
}
