package com.leetcode.medium.bfsdfs;

import com.util.TreeNode;

/**
 * #98. Validate Binary Search Tree
 *
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 *
 *     2
 *   /   \
 *  1     3
 * Input: root = [2,1,3]
 * Output: true
 *
 * Example 2:
 *     5
 *   /   \
 *  1     4
 *      /  \
 *     3    6
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        // Example 1:
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        System.out.println(isValidBST(root)); // true

        // Example 2:
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);

        System.out.println(isValidBST(root2)); // false
    }

    public static boolean isValidBST(TreeNode root) {
        return valid(root, null, null); // nulls instead of Integer.MIN/MAX, because there's a test case
    }

    private static boolean valid(TreeNode node, Integer min, Integer max) {
        if (node == null) return true; // technically a valid BST

        // all these non-null checks just to bypass Integer.MAX/MIN issue, they have a test case for it
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }

        return valid(node.left, min, node.val) && valid(node.right, node.val, max);
    }
}
