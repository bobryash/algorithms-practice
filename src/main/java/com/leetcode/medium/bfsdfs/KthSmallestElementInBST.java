package com.leetcode.medium.bfsdfs;

import com.util.TreeNode;

/**
 * #230. Kth Smallest Element in a BST
 *
 * Given the root of a binary search tree, and an integer k, return the kth smallest value
 * (1-indexed) of all the values of the nodes in the tree.
 *
 * Example 1:
 *     3
 *   /  \
 *  1    4
 *   \
 *    2
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Example 2:
 *         5
 *       /   \
 *      3     6
 *    /   \
 *   2     4
 *  /
 * 1
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *
 *
 */
public class KthSmallestElementInBST {

    public static void main(String[] args) {
        // Example 1:
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println(kthSmallest(root, 1)); // 1

        // Example 2:
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.left.left.left = new TreeNode(1);

        System.out.println(kthSmallest(root1, 3)); // 3
    }

    static int count = 0;
    static int result = 0;

    public static int kthSmallest(TreeNode root, int k) {
        inOrderTraverse(root, k);
        return result;
    }

    // the idea - in order traverse ensures that we go in ascending order.
    // what is left is to stop when count == k
    private static void inOrderTraverse(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        inOrderTraverse(node.left, k);

        count++;
        if (k == count) {
            result = node.val;
            return;
        }

        inOrderTraverse(node.right, k);
    }
}
