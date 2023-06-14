package com.leetcode.easy.recursionbacktracking;

import com.util.ListNode;

/**
 * #206
 * <p>
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode list = new ListNode(1)
                .next(new ListNode(2)
                        .next(new ListNode(3)
                                .next(new ListNode(4)
                                        .next(new ListNode(5)))));

        System.out.println(list); // [1, 2, 3, 4, 5]
        ListNode result = reverseIterative(list);
        System.out.println(result); // [5, 4, 3, 2, 1]
    }

    /* iterative solution */
    public static ListNode reverseIterative(ListNode head) {
        // null 1 -> 2 -> 3
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next; // save next (2 from example above), because we lose it otherwise
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /* recursive solution */
    public static ListNode reverseRecursive(ListNode head) {
        return reverseListInt(head, null);
    }

    private static ListNode reverseListInt(ListNode head, ListNode newHead) {
        if (head == null) {
            return newHead;
        }

        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);

    }
}
