package com.leetcode.medium.bfsdfs;

import com.util.TreeNode;

/**
 * #285. Inorder Successor in BST
 * 
 * Given a binary search tree (BST) and a node in it,
 * find the in-order successor of that node in the BST.
 * The in-order successor of a node in a BST is the node with
 * the smallest key greater than the key of the given node.
 * 
 * You may assume that each node has a unique value.
 * 
 * Example 1:
 * Input: target = 2
 *   2
 *  / \
 * 1   3
 * Output: 3
 * 
 * Explanation:
 * In the above BST, the in-order successor of
 * the node with value 2 is the node with value 3.
 * 
 * 
 * Example 2:
 *
 * Input: target = 2
 *     5
 *    / \
 *   3   6
 *  / \
 * 2   4
 * /
 * 1
 * Output: 3
 * 
 * Explanation:
 * In the above BST, the in-order successor of
 * the node with value 2 is the node with value 3.
 * 
 * Note:
 * 
 * If the given node has no in-order successor in the BST, return null.
 * It's guaranteed that the values of the BST are unique.
 */
public class InorderSuccessorInBST {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println(findInorderSuccessor(root1, new TreeNode(2))); // 3

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);
        System.out.println(findInorderSuccessor(root1, new TreeNode(2))); // 3
    }

    // O(logN) time, O(1) space
    // the idea - to search successor with binary search:
    // if current node > target, than we found intermediate successor,
    // and we can continue to search even lesser successors in a left tree (with lesser values).
    // if current node < target, then keep searching in a right tree (with bigger values).
    // do it until we get to a leaf node.
    public static TreeNode findInorderSuccessor(TreeNode root, TreeNode target) {
        TreeNode successor = null;

        while (root != null) {
            if (root.val > target.val) {
                // if current root val is more than target - possible nearest successor
                successor = root;
                // continue to search lesser successor in a lesser tree
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return successor;
    }

    //---------------------------------------
    // my clumsy O(N) time
    static TreeNode result = null;

    public static TreeNode findInorderSuccessor2(TreeNode node, TreeNode target) {
        helper(node, target);
        return result;

    }

    public static void helper(TreeNode node, TreeNode target) {
        if (node == null) {
            return;
        }

        findInorderSuccessor2(node.left, target);
        if (node.val > target.val && (result == null || node.val < result.val)) {
            result = node;
        }
        findInorderSuccessor2(node.right, target);
    }
}
