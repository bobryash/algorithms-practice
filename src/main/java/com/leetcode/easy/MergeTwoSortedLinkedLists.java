package com.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Merge to sorted linked lists.
 * Example
 * Input:   1 - 3 - 4
 *          1 - 2 - 4
 * Result:  1 - 1 - 2 - 3 - 4 - 4
 */
public class MergeTwoSortedLinkedLists {

    public static void main(String[] args) {
        new MergeTwoSortedLinkedLists().test();
    }

    public void test2() {
        int x1 = 1;
        int x2 = x1;
        x1 = 3;
        System.out.println("here " +x2);

        String s1 = "yes";
        String s2 = s1;
        s1 = "no";
        System.out.println("here " +s2);
    }

    public void test() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(4);

        System.out.println("input: " + l1 + " " + l2);
        String expected = "[1, 1, 2, 3, 4, 4]";
        String result = mergeTwoLists3(l1, l2).toString();
        System.out.println(result + " - result" + "\n" + expected + " - expected");
        System.out.println(expected.equals(result));
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

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
        curr.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (true) {
            if (l1 == null) {
                dummy.next = l2;
                break;
            }
            if (l2 == null) {
                dummy.next = l1;
                break;
            }
            if (l1.val <= l2.val) {
                dummy.next = l1;
                l1 = l1.next;
            } else {
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
            System.out.println("cur dum: " + dummy);
            System.out.println("cur head:" + head + "\n");
        }
        return head.next;
    }

    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
    // ListNode definition
    public class ListNode {
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
