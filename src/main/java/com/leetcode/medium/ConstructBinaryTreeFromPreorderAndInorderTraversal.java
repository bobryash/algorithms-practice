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

    // something about tree traversals:
    //
    // 1. preorder array will always have a root in the beginning index
    //               3
    //             /   \
    //            9     20
    //                 /  \
    //                15   7
    // preorder (visit-left-right): [3(root), 9, 20, 15, 7]
    //
    // 2. in inorder array, all elements to the left side from root node belong to the left subtree,
    // all elements on the right side - to the right subtree
    //               3
    //             /   \
    //            9     20
    //                 /  \
    //                15   7
    // inorder (left-visit-right): [9, 3, 15, 20, 7] - [9] 3(root) [15, 20, 7]
    //
    // so the idea - get root from preorder(always first), create node, then find index of that node in inorder,
    // so we can calculate size of a left tree(by using above rules), then create left and right nodes
    // by cutting left and right subtrees in inorder and preorder arrays by measuring them:
    //
    // - measure preorder with left tree size
    //  [3, (9 left subtree= root(arrays start)+1<->root+leftTreeSize), (20, 15, 7 right subtree = root+1+leftTree<->endOfArray)]
    //
    // - measure inorder with inorder root index:
    //  [(9 left subtree = array start<->root - 1), 3(root index), (20, 15, 7 right subtree(root + 1 <-> end of array]
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return treeBuilder(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private static TreeNode treeBuilder(int[] preorder, int preStart, int preEnd,
                                        int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootValue = preorder[preStart]; // nature of preorder - root always be at the very beginning
        TreeNode root = new TreeNode(rootValue);

        int inOrderRootIndex = getIndex(inorder, rootValue, inStart, inEnd); // now can find root in inorder
        int leftTreeSize = inOrderRootIndex - inStart; // and calculate size of a left tree

        // to figure all of these indexes just look at the arrays and the original tree, it's almost easy
        root.left = treeBuilder(preorder, preStart + 1, preStart + leftTreeSize,
                inorder, inStart, inOrderRootIndex - 1);
        root.right = treeBuilder(preorder, preStart + 1 + leftTreeSize, preEnd,
                inorder, inOrderRootIndex + 1, inEnd);

        return root;
    }

    private static int getIndex(int[] inorder, int target, int start, int end) {
        for (int i = start; i <= end; i++) { // <= because we use 0-based indexes
            if (target == inorder[i]) {
                return i;
            }
        }

        return -1; // shouldn't happen in valid input
    }
}
