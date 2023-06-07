package com.leetcode.easy.bfsdfs;

/**
 * #101. Symmetric Tree
 * <p>
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 * <p>
 * Example 1:
 * <p>
 *       1
 *     /   \
 *    2     2
 *   / \   / \
 *  3   4 4   3
 * <p>
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * <p>
 * Example 2:
 * <p>
 *       1
 *     /   \
 *    2     2
 *     \     \
 *      3     3
 * <p>
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 */
public class SymmetricTree {
    public static void main(String[] args) {
        // 1
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(4)),
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(3)));
        System.out.println(isSymmetric(root1)); // true

        // 2
        TreeNode root2 = new TreeNode(1,
                new TreeNode(2,
                        null,
                        new TreeNode(3)),
                new TreeNode(2,
                        null,
                        new TreeNode(3)));
        System.out.println(isSymmetric(root2)); // false
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }

        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val &&
                isMirror(left.left, right.right) &&
                isMirror(left.right, right.left);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
