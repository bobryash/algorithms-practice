package com.leetcode.easy.twopointers;

import com.util.ListNode;

/**
 * #160. Intersection of Two Linked Lists
 * <p>
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 * <p>
 * For example, the following two linked lists begin to intersect at node c1:
 *       a1 -> a2 \
 *                  c1 -> c2 -> c3
 * b1 -> b2 -> b3 /
 * <p>
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 * <p>
 * Note that the linked lists must retain their original structure after the function returns.
 * <p>
 * Example 1:
 * 5 -> 6 -> 1 \
 *              8 -> 4 -> 5
 *      4 -> 1 /
 * Output: Intersected at '8'
 * <p>
 * Example 2:
 * 2 -> 6 -> 4
 *      1 -> 5
 * Output: No intersection
 */

public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {
        testHaveIntersection();
        testHaveNoIntersection();
    }

    // the idea: two list go through the exact same path: listALenght + listBLength.
    // they either both reach null - if there is no intersection
    // or reach theirs intersection.
    // (check the examples' pictures)
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode currentA = headA;
        ListNode currentB = headB;

        while (currentA != currentB) {
            currentA = (currentA == null) ? headB : currentA.next;
            currentB = (currentB == null) ? headA : currentB.next;
        }

        return currentB; // doesn't matter, could return currentA
    }

    private static void testHaveIntersection() {
        // A
        ListNode node11 = new ListNode(5);
        ListNode node12 = new ListNode(6);
        ListNode node13 = new ListNode(1);
        node11.next = node12;
        node12.next = node13;

        // B
        ListNode node21 = new ListNode(4);
        ListNode node22 = new ListNode(1);
        node21.next = node22;

        // common
        ListNode node31 = new ListNode(8);
        ListNode node32 = new ListNode(4);
        ListNode node33 = new ListNode(5);

        node13.next = node31;
        node22.next = node31;

        node31.next = node32;
        node32.next = node33;

        System.out.println(getIntersectionNode(node11, node21).val); // 8
    }

    private static void testHaveNoIntersection() {
        // A
        ListNode node11 = new ListNode(2);
        ListNode node12 = new ListNode(6);
        ListNode node13 = new ListNode(14);
        node11.next = node12;
        node12.next = node13;

        // B
        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(5);
        node21.next = node22;

        System.out.println(getIntersectionNode(node11, node21)); // null
    }
}
