package com.leetcode.hard;

import com.util.ListNode;

import java.util.Arrays;
import java.util.List;

/**
 * #23. Merge k Sorted Lists
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
        // [[1,4,5],[1,3,4],[2,6]]
        ListNode head1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode head2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode head3 = new ListNode(2, new ListNode(6));

        List<ListNode> listNodes = Arrays.asList(head1, head2, head3);
        System.out.println(listNodes.toArray(new ListNode[0]).length);
        ListNode result = mergeKLists(listNodes.toArray(new ListNode[0]));

        // [1,1,2,3,4,4,5,6]
        System.out.println(result.toString());

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        int size = lists.length;
        int interval = 1;

        while (interval < size) {
            for (int i = 0; i < size - interval; i += 2 * interval) {
                lists[i] = merge(lists[i], lists[i + interval]);
            }

            interval *= 2;
        }

        return size > 0 ? lists[0] : null;
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        if (l1 != null) {
            curr.next = l1;
        } else {
            curr.next = l2;
        }

        return dummy.next;
    }
}
