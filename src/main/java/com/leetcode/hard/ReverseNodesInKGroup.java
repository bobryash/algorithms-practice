package com.leetcode.hard;

import com.util.ListNode;

/**
 * #25. Reverse Nodes in k-Group
 * <p>
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * <p>
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 * <p>
 * 1 -> 2 -> 3 -> 4 -> 5
 * 2 -> 1 -> 4 -> 3 -> 5
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode res = reverseKGroup(head, 2);

        System.out.println(res.toString());
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) {
                break;
            }
            ListNode groupAfter = kth.next;

            // Reverse group
            ListNode prev = kth.next; // 1 -> 2 -> 3. you need to reverse 1->2 to 2->1, and then attach 1 to 3, so 3 becomes prev (instead of null, as in classic linkedlist reverse problem)
            ListNode curr = groupPrev.next;
            while (curr != groupAfter) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // groupPrev.next - was first in a group, kth was the last one. now we need to swap them
            ListNode tmp = groupPrev.next;
            groupPrev.next = kth; // update dummy to 0 -> 2 -> 1 -> 3 -> 4 -> 5
            groupPrev = tmp; // need to update groupPrev from 0->1 to
            System.out.println();
        }

        return dummy.next;
    }

    public static ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}
