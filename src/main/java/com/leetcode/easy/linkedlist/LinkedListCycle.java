package com.leetcode.easy.linkedlist;

import com.util.ListNode;

/**
 * #141. Linked List Cycle
 *
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that
 * can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
 * Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        // 1
        // Create nodes
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        // Connect nodes
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        // Create the cycle
        node4.next = node2;

        System.out.println(hasCycle(node1)); // true

        // 2
        System.out.println(hasCycle(new ListNode(1).next(new ListNode(2)))); // false
    }

    // Floyd's Tortoise and Hair algorithm (check pics)
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
