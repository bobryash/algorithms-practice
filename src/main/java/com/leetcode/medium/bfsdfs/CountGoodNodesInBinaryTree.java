package com.leetcode.medium.bfsdfs;

import com.util.TreeNode;

/**
 * #1448. Count Good Nodes in Binary Tree
 *
 * Given a binary tree root, a node X in the tree is named good if in
 * the path from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 *
 * Example 1:
 *        3(g)
 *      /   \
 *     1     4(g)
 *   /     /  \
 *  3(g)  1    5(g)
 *
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 *
 * Example 2:
 *      3(g)
 *     /
 *    3(g)
 *   /  \
 *  4(g) 2
 *
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 *
 * Example 3:
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 */
public class CountGoodNodesInBinaryTree {

    public static void main(String[] args) {
        // Example 1:
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.left = new TreeNode(3);
        root1.right.left = new TreeNode(1);
        root1.right.right = new TreeNode(5);

        System.out.println(goodNodes(root1)); // 4

        // Example 2:
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(2);

        System.out.println(goodNodes(root2)); // 3
    }

    // the idea - compare each node's value to previous maxValue up the tree.
    // start with root (you compare it with itself, hence it'll always be a good node),
    // then pass current max value to children and keep comparing
    public static int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    public static int dfs(TreeNode node, int maxValue) {
        if (node == null) return 0;

        int result = node.val >= maxValue ? 1 : 0;
        result += dfs(node.left, Math.max(node.val, maxValue));
        result += dfs(node.right, Math.max(node.val, maxValue));

        return result;
    }
}
