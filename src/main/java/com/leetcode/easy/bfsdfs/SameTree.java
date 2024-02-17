package com.leetcode.easy.bfsdfs;

import com.util.TreeNode;

/**
 * #100. Same Tree
 *
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 * Example 1:
 *       1         1
 *     /   \     /   \
 *    2     3   2     3
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 *
 *
 * Example 2:
 *    1    1
 *   /      \
 *  2        2
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 *
 *
 * Example 3:
 *     1         1
 *   /   \     /   \
 *  2     1   1     2
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 */
public class SameTree {
    public static void main(String[] args) {
        // Ex 1
        TreeNode root11 = new TreeNode(1);
        root11.left = new TreeNode(2);
        root11.right = new TreeNode(3);

        TreeNode root12 = new TreeNode(1);
        root12.left = new TreeNode(2);
        root12.right = new TreeNode(3);

        System.out.println(isSameTree(root11, root12)); // true

        // Ex 2
        TreeNode root21 = new TreeNode(1);
        root21.left = new TreeNode(2);

        TreeNode root22 = new TreeNode(1);
        root22.right = new TreeNode(2);

        System.out.println(isSameTree(root21, root22)); // false

        // Ex 3
        TreeNode root31 = new TreeNode(1);
        root31.left = new TreeNode(2);
        root31.right = new TreeNode(1);

        TreeNode root32 = new TreeNode(1);
        root32.left = new TreeNode(1);
        root32.right = new TreeNode(2);

        System.out.println(isSameTree(root31, root32)); // false
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
