package com.leetcode.medium.bfsdfs;

/**
 * #116
 * <p>
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 * The binary tree has the following definition:
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Example:
 * <p>
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to
 * point to its next right node, just like in Figure B. The serialized output is in level order as connected by the
 * next pointers, with '#' signifying the end of each level.
 */
public class PopulatingNextRightPointersInEachNode {

    // BFS
    // This solution uses two while loops to traverse the tree level by level.
    // The first while loop is used to move down to the leftmost node of the next level, and the second while loop is
    // used to connect the nodes of the current level.
    //
    // At the first while loop, it start from the leftmost node of the current level, and in the second while loop,
    // it connects the left child of the current node to the right child of the same node, and then it connects the
    // right child of the current node to the left child of the next node.
    //
    // It is O(N) time complexity, where N is the total number of nodes, and O(1) space complexity.
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node leftmost = root;
        while (leftmost.left != null) {
            Node head = leftmost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }

        return root;
    }

    // DFS (easy)
    // It is a DFS algorithm, that means it will traverse the left and right subtree first, it will use recursion to
    // go deep down to the leaf node, then it will come back and connect all the nodes.
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }

        connect2(root.left);
        connect2(root.right);

        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
