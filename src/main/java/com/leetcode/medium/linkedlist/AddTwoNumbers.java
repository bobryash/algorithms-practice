package com.leetcode.medium.linkedlist;

import com.util.ListNode;

/**
 * #2. Add Two Numbers
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        System.out.println(addTwoNumbers(new ListNode(2).next(new ListNode(4).next(new ListNode(3))),
                new ListNode(5).next(new ListNode(6).next(new ListNode(4))))); // 7 -> 0 -> 8

        System.out.println(addTwoNumbers(new ListNode(0),
                new ListNode(0))); // 0
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); // static, holds the root
        ListNode curr = dummy; // dynamic, moves along the logic

        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            int sum = x + y + carry;
            carry = sum / 10; // ostatok, 7/10 = 0, 14/10 = 1; to pass it to the next sum

            // modulus operator!
            // If the dividend (sum) is smaller than the divisor (10),
            // the result of the modulus operation will be equal to the dividend.
            // or it will return reminder of division
            // 7 % 10 = 7, 14 % 10 = 4
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }

        // don't forget the last updated carry!!!
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummy.next;
    }
}
