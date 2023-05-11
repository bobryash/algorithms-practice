package com.leetcode.easy.recursionbacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * #21
 * You are given the heads of two sorted linked lists list1 and list2.
 * <p>
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of
 * the first two lists.
 * <p>
 * Return the head of the merged linked list.
 * <p>
 * Example:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 */
public class MergeTwoSortedLinkedLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        String result = mergeTwoLists2(l1, l2).toString();

        System.out.println(result); // [1, 1, 2, 3, 4, 4]
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * The solution iteratively compares the values of the two input lists and appends the smaller value to a new list
     * until one of the lists is exhausted. Then, it appends the remaining nodes from the non-empty list
     * to the end of the new list.
     *
     * initially prehead and cur point to the same dummy object
     * prehead is keeping all chain.
     * cur says: now next object of the current one is THIS, and after it I become THIS (assign `next` and become next = easy chain traverse)
     * without cur you should traverse prehead to the end each time.
     *
     * if use only prehead, then by prehead = prehead.next you just losing links
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1); // -1 is just random number
        ListNode cur = prehead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

    // ListNode definition
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
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
