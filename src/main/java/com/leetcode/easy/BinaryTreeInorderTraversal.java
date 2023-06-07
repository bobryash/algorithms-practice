package com.leetcode.easy;

import com.util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
    #94. Given the root of a binary tree,return the inorder traversal of its nodes' values.

    Example 1:
    Input:root=[1,null,2,3]
    Output:[1,3,2]

    Example 2:
    Input:root=[]
    Output:[]

    Example 3:
    Input:root=[1]
    Output:[1]
*/

// check chap08.binarytree package to remember
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,           //    1
                null,                                 //     \
                new TreeNode(2,                   //      2
                        new TreeNode(3,           //     /
                                null,                 //     3
                                null),
                        null));

        List<Integer> result = inorderTraversal(root);
        System.out.println(Arrays.toString(result.toArray())); // 1, 3, 2
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(result, root);

        return result;
    }

    private static void helper(List<Integer> list, TreeNode root) {
        if (root != null) {
            helper(list, root.left);
            list.add(root.val);
            helper(list, root.right);
        }
    }
}
