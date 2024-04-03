package com.leetcode.easy.bfsdfs;

import com.util.TreeNode;

/**
 * #543. Diameter of Binary Tree
 *
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter (*here - number of edges, not nodes) of a binary tree
 * is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 *
 *        1
 *      /   \
 *     2     3
 *    / \
 *   4   5
 *
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 *
 * Example 2:
 *
 * Input: root = [1,2]
 * Output: 1
 */
public class DiameterOfBinaryTree {

    public static void main(String[] args) {
        // Example usage:
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();

        // Construct a binary tree (example)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Calculate and print the diameter
        int diameter = solution.diameterOfBinaryTree(root);
        System.out.println("Diameter of the binary tree: " + diameter);
    }

    // instance variable, not local, so it's available in all methods
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeightAndUpdateDiameterInMeanwhile(root);
        return diameter;
    }

    // think of diameter and height.
    // in the context of a one specific node - diameter is the path, which goes through that node from left to right children.
    // height is a max level down (level = child.child.child...). 1 node = 1 level
    private int calculateHeightAndUpdateDiameterInMeanwhile(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively calculate the heights of the left and right subtrees
        int leftHeight = calculateHeightAndUpdateDiameterInMeanwhile(node.left);
        int rightHeight = calculateHeightAndUpdateDiameterInMeanwhile(node.right);

        // Update the diameter
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return the height of the current subtree
        return 1 + Math.max(leftHeight, rightHeight); // + 1 to include the height of the current node
    }
}
