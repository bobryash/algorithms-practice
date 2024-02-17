package com.leetcode.medium.bfsdfs;

import com.util.TreeNode;

/**
 * #235. Lowest Common Ancestor of a Binary Search Tree
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes
 * p and q as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 *
 *         6
 *      /    \
 *     2      8
 *    / \    /  \
 *   0   4  7    9
 *      / \
 *     3   5
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 *
 * Example 2:
 *
 *          6
 *       /      \
 *      2        8
 *    /   \    /   \
 *   0     4  7     9
 *        / \
 *       3   5
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 *
 *
 * Example 3:
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 */
public class LowestCommonAncestorOfBinarySearchTree {

    public static void main(String[] args) {
        // Ex 1
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(9);
        root1.left.right.left = new TreeNode(3);
        root1.left.right.right = new TreeNode(5);

        System.out.println(lowestCommonAncestor(root1, new TreeNode(2), new TreeNode(8))); // 6

        // Ex 2
        TreeNode root2 = new TreeNode(6);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(8);
        root2.left.left = new TreeNode(0);
        root2.left.right = new TreeNode(4);
        root2.right.left = new TreeNode(7);
        root2.right.right = new TreeNode(9);
        root2.left.right.left = new TreeNode(3);
        root2.left.right.right = new TreeNode(5);

        System.out.println(lowestCommonAncestor(root2, new TreeNode(2), new TreeNode(4))); // 2

        // Ex 3
        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(1);

        System.out.println(lowestCommonAncestor(root3, new TreeNode(2), new TreeNode(1))); // 2
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (p.val > root.val && q.val > root.val) { // Both nodes are in the left subtree
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) { // Both nodes are in the right subtree
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // One node is in the left subtree, and the other is in the right subtree
            // or one of the nodes is the current node (which is ok)
            return root;
        }
    }
}
