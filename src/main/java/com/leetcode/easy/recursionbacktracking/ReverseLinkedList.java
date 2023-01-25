package com.leetcode.easy.recursionbacktracking;

import java.util.ArrayList;
import java.util.List;

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
        ListNode result = reverseList1(list);
        System.out.println(result); // [5, 4, 3, 2, 1]
    }

    /* iterative solution */
    public static ListNode reverseList1(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    /* recursive solution */
    public static ListNode reverseList2(ListNode head) {
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

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode next(ListNode node) {
            this.next = node;
            return this;
        }

        @Override
        public String toString() {
            ListNode listNode = this;
            List<Integer> result = new ArrayList<>();
            while (listNode != null) {
                result.add(listNode.val);
                listNode = listNode.next;
            }
            return result.toString();
        }
    }
}
