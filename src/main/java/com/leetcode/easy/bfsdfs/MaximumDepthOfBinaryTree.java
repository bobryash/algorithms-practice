package com.leetcode.easy.bfsdfs;

import com.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * #104. Maximum Depth of Binary Tree
 * <p>
 * Given the root of a binary tree, return its maximum depth.
 * <p>
 * A binary tree's maximum depth is the number of nodes along the longest path from the
 * root node down to the farthest leaf node.
 * <p>
 * Example 1:
 *     3
 *   /   \
 *  9    20
 *      /  \
 *     15   7
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1,null,2]
 * Output: 2
 *
 */
public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));

        System.out.println(maxDepthDFS(root1)); // 3
        System.out.println(maxDepthBFS(root1)); // 3
        System.out.println(maxDepthDFSIterative(root1)); // 3

        // Example 2
        TreeNode root2 = new TreeNode(1,
                null,
                new TreeNode(2));

        System.out.println(maxDepthDFS(root2)); // 2
        System.out.println(maxDepthBFS(root2)); // 2
        System.out.println(maxDepthDFSIterative(root2)); // 2

        // Example 3 (got a bug on ..; i < queue.size();.. with this case)
        TreeNode root3 = new TreeNode(0,                             //            0
                new TreeNode(2,                                      //          /   \
                        new TreeNode(1,                              //         2     4
                                new TreeNode(5),                     //        /     / \
                                new TreeNode(1)),                    //       1     3   -1
                        null),                                       //      / \     \    \
                new TreeNode(4,                                      //     5   1     6    8
                        new TreeNode(3,
                                null,
                                new TreeNode(6)),
                        new TreeNode(-1,
                                null,
                                new TreeNode(8))));

        System.out.println(maxDepthDFS(root3)); // 4
        System.out.println(maxDepthBFS(root3)); // 4
        System.out.println(maxDepthDFSIterative(root3)); // 4
    }

    // 1. Depth-first search (DFS)
    public static int maxDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepthDFS(root.left), maxDepthDFS(root.right));
    }

    // 2. Breadth-first search (BFS)
    public static int maxDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // dynamic nature of the queue! queue will be updated in the loop and size will be changed. so you should store initial size beforehand
            for (int i = 0; i < levelSize; i++) { // so don't even think about ..; i < queue.size();..
                TreeNode currentNode = queue.poll();

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            depth++;
        }

        return depth;
    }


    // 3. DFS iterative
    public static int maxDepthDFSIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> levelStack = new Stack<>();

        nodeStack.push(root);
        levelStack.push(1);

        int maxDepth = 0;

        // preorder traversal (itself, then left, then right)
        while (!nodeStack.isEmpty()) {
            TreeNode currentNode = nodeStack.pop();
            Integer currentLevel = levelStack.pop();

            if (currentNode.left == null && currentNode.right == null) { // if we reached a leaf
                maxDepth = Math.max(maxDepth, currentLevel);
            }

            if (currentNode.left != null) {
                nodeStack.push(currentNode.left);
                levelStack.push(currentLevel + 1);
            }

            if (currentNode.right != null) {
                nodeStack.push(currentNode.right);
                levelStack.push(currentLevel + 1);
            }
        }

        return maxDepth;
    }
}
