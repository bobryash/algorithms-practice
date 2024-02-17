package com.leetcode.easy.bfsdfs;

import com.util.TreeNode;

/**
 * #572. Subtree of Another Tree
 *
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root
 * with the same structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree 'tree' is a tree that consists of a node in tree and all of this node's descendants.
 * The tree 'tree' could also be considered as a subtree of itself.
 *
 * Example 1:
 *       3
 *     /   \
 *    4     5     4
 *   / \         / \
 *  1   2       1   2
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 *
 *
 * Example 2:
 *       3
 *     /   \
 *    4     5     4
 *   / \         / \
 *  1   2       1   2
 *     /
 *    0
 * Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * Output: false
 */
public class SubtreeOfAnotherTree {

    public static void main(String[] args) {
        // Ex 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(2);

        TreeNode subTree1 = new TreeNode(4);
        subTree1.left = new TreeNode(1);
        subTree1.right = new TreeNode(2);

        System.out.println(isSubtree(root1, subTree1)); // true

        // Ex 2
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(2);
        root2.left.right.left = new TreeNode(0);

        TreeNode subTree2 = new TreeNode(4);
        subTree2.left = new TreeNode(1);
        subTree2.right = new TreeNode(2);

        System.out.println(isSubtree(root2, subTree2)); // false
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // edge cases
        if (root == null) { // null tree can't have subtrees
            return false;
        }

        if (subRoot == null) { // think of a leaf node - it has nulls as left and right "children"
            return true;
        }

        // first question - are root and subRoot the same
        if (sameTree(root, subRoot)) {
            return true;
        }

        // second question - is subRoot is the subRoot of left and right children?
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean sameTree(TreeNode s, TreeNode q) {
        // edge cases
        if (s == null && q == null) {
            return true;
        }

        if (s == null || q == null) {
            return false;
        }

        // dfs
        return s.val == q.val
                && sameTree(s.left, q.left) && sameTree(s.right, q.right);
    }
}
