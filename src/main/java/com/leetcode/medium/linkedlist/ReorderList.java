package com.leetcode.medium.linkedlist;

import com.util.ListNode;

/**
 * #143. Reorder List
 * <p>
 * You are given the head of a singly linked-list. The list can be represented as:
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * <p>
 * Example 2:
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 */
public class ReorderList {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1).next(new ListNode(2).next(new ListNode(3).next(new ListNode(4))));
        reorderList(list1);
        System.out.println(list1);

        ListNode list2 = new ListNode(1).next(new ListNode(2).next(new ListNode(3).next(new ListNode(4).next(new ListNode(5)))));
        reorderList(list2);
        System.out.println(list2);
    }

    // the idea - cut list in half, then reorder second part, then merge these parts
    public static void reorderList(ListNode head) {
        // 1. Cut list in halves
        ListNode slow = head;
        // this way (fast = head.next) slow will be a tail of a first half (check photo in notes).
        // if list is odd sized, then we get smaller part as a second half, because it's easier to merge it later.
        // Example:
        // halving list: 1 2 3(slow) 4 5 null(fast) -> [1,2,3(slow)] [4,5]; (slow here is a tail of first half!)
        // reversing second part: [1,2,3], [5,4]
        // merging: while 2half(smaller) != null: 1-5-2-4-3
        // (for odd sized array it's the same and easier)
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next; // 'second' now points to the second half of list
        // slow.next = null -super important, because that way we effectively cut the tail, and head points only to 1st half now
        // (cause 'slow' points to a part of head list, so if we change 'slow', head is affected by it)
        slow.next = null;

        // 2. Reverse the second half of the list
        ListNode prev = null;
        while (second != null) {
            ListNode tmp = second.next;
            second.next = prev;
            prev = second;
            second = tmp;
        }

        // 3. Merge two halves
        ListNode first = head;
        second = prev;
        while (second != null) { // because 2nd is a smaller half
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;

            first.next = second;
            second.next = tmp1;

            first = tmp1;
            second = tmp2;
        }
    }

    // it's unfinished, but gives you an idea about how cutting in two halves works,
    // and why it's important to have a tail reference of a first half
    public static void unfinishedReordered(ListNode head) {
        if (head == null || head.next == null)
            return;

        // 1. Cut the list in two halves
        ListNode slow = head, fast = head;
        ListNode tail = null; // super important to track a tail of first half, so we can cut it (head=first half only)

        while (fast != null && fast.next != null) {
            tail = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        tail.next = null; // now head points to first half

        // 2. Reverse second part.
        // at this point 'slow' is a pure second half, and head is first half.
        ListNode prev = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }

        // 3. Merge lists
        ListNode first = head;
        ListNode second = prev; // prev is the head of the reversed second half
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;

            first.next = second;
            second.next = tmp1;

            first = tmp1;
            second = tmp2;
        }
    }
}
