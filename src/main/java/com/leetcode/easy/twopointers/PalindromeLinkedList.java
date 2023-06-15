package com.leetcode.easy.twopointers;

import com.util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * #234. Palindrome Linked List
 * Given the head of a singly linked list, return true if it is a
 * palindrome or false otherwise.
 * <p>
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 * <p>
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {
        ListNode palindromeList = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        ListNode nonPalindromeList = new ListNode(1, new ListNode(2));

        System.out.println(isPalindromeExtraSpace(palindromeList)); // true
        System.out.println(isPalindromeExtraSpace(nonPalindromeList)); // false

        System.out.println(isPalindromeConstantTime(palindromeList)); // true
        System.out.println(isPalindromeConstantTime(nonPalindromeList)); // false
    }

    // O(n) space
    public static boolean isPalindromeExtraSpace(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // O(1) space
    public static boolean isPalindromeConstantTime(ListNode head) {
        // find the middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse from the middle (slow)
        ListNode prev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // check if palindrome (prev is the new middle!)
        while (prev != null) {
            if (head.val != prev.val) {
                return false;
            }
            head = head.next;
            prev = prev.next;
        }

        return true;
    }
}
