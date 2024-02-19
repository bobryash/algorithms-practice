package com.leetcode.medium;

import com.util.TreeNode;

import java.util.Arrays;

/**
 * #105. Construct Binary Tree from Preorder and Inorder Traversal
 *
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * Example 1:
 *       3
 *     /   \
 *    9     20
 *         /  \
 *        15   7
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        System.out.println(buildTree(
                new int[]{3, 9, 20, 15, 7}, // preorder: root-left-right. root is always first, then left+right subtrees
                new int[]{9, 3, 15, 20, 7}) // inorder: left-root-right. root is somewhere in the middle, left and right trees on a left a and right sides
        );
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return treeBuilder(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    // the idea is to know how to divide preorder and inorder arrays, so we can find the root in them, and then extract left and right trees relative to the root
    private static TreeNode treeBuilder(int[] preorder, int preStart, int preEnd,
                                        int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootValue = preorder[preStart]; // nature of a preorder - root always be at the very beginning
        TreeNode root = new TreeNode(rootValue);

        int inOrderRootIndex = getIndex(inorder, rootValue, inStart, inEnd); // now can find root in inorder
        int leftTreeSize = inOrderRootIndex - inStart; // and calculate size of a left tree

        // to figure all of these indexes just look at the arrays and the original tree, it's almost easy
        root.left = treeBuilder(preorder, inStart + 1, inStart + leftTreeSize,
                inorder, inStart, inOrderRootIndex - 1);
        root.right = treeBuilder(preorder, inStart + 1 + leftTreeSize, preEnd,
                inorder, inOrderRootIndex + 1, inEnd);

        return root;
    }

    private static int getIndex(int[] inorder, int target, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (target == inorder[i]) {
                return i;
            }
        }

        return -1; // shouldn't happen in valid input
    }
}
