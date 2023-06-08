package com.leetcode.easy.binarysearch;

import com.util.TreeNode;

/**
 * #108. Convert Sorted Array to Binary Search Tree
 * <p>
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a
 * height-balanced binary search tree.
 * <p>
 * Example:
 * Input: nums = [-10,-3,0,5,9]
 * Output:
 *         0
 *        / \
 *      -3   9
 *      /   /
 *    -10  5
 * <p>
 * Also ok:
 *         0
 *        / \
 *      -10  5
 *        \   \
 *        -3   9
 */
public class ConvertSortedArrayToBinarySearchTree {

    public static void main(String[] args) {
        TreeNode result = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(result.toString());
    }

    // the idea: create root node by finding mid value in array.
    // then recursively do the same with left and right parts of an array, relative to mid.
    // recursion base case is when left > right
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return helper(nums, 0, nums.length - 1);
    }

    private static TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);

        return root;
    }
}
