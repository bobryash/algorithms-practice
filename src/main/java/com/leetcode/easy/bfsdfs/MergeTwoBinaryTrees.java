package com.leetcode.easy.bfsdfs;

import com.util.TreeNode;

/**
 * #617
 * <p>
 * You are given two binary trees root1 and root2.
 * <p>
 * Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the
 * others are not.
 * You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap,
 * then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as
 * the node of the new tree.
 * <p>
 * Return the merged tree.
 * (sounds confusing, but check the image, it will make sense)
 * <p>
 * Note: The merging process must start from the root nodes of both trees.
 * <p>
 * Example 1:
 * Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * Output: [3,4,5,5,4,null,7]
 * <p>
 * Example 2:
 * Input: root1 = [1], root2 = [1,2]
 * Output: [2,2]
 */
public class MergeTwoBinaryTrees {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.setLeft(new TreeNode(3).setLeft(new TreeNode(5)));
        root1.setRight(new TreeNode(2));

        TreeNode root2 = new TreeNode(2);
        root2.setLeft(new TreeNode(1).setRight(new TreeNode(4)));
        root2.setRight(new TreeNode(3).setRight(new TreeNode(7)));

        TreeNode result = new MergeTwoBinaryTrees().mergeTrees(root1, root2);
        System.out.println(result);
    }

    // The solution uses a recursive approach to traverse through the tree.
    // The base case is when one of the trees is null, in that case the non-null tree is returned.
    // If both trees are not null, the values of the current nodes are added
    // and the left and right subtrees are merged recursively.
    //
    // The solution will run on O(min(N1, N2)) where N1 and N2 are the number of nodes in the first and second
    // tree respectively.
    // It uses O(min(H1, H2)) space where H1 and H2 are the height of the first and second tree respectively.
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }
}
