package com.leetcode.medium.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * #82
 * <p>
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * <p>
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * <p>
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 */
public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {
        ListNode list = new ListNode(1)
                .next(new ListNode(2)
                        .next(new ListNode(3)
                                .next(new ListNode(3)
                                        .next(new ListNode(4)
                                                .next(new ListNode(4)
                                                        .next(new ListNode(5)))))));

        ListNode result = RemoveDuplicatesFromSortedList.deleteDuplicates(list); // [1, 2, 3, 3, 4, 4, 5]
        System.out.println(result); // [1, 2, 5]
    }

    // Here, we create a dummy node to act as the head of the list and initialize a prev node to the dummy node.
    // We then iterate over the linked list using the head node. If we find a node with a duplicate value,
    // we skip over all the nodes with the same value until we find a node with a different value, and then we update
    // the prev node to point to the next node. If we find a node with a distinct value, we update the prev node
    // to point to the current node.
    // Finally, we return the next node of the dummy node, which is the actual head of the list.
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                // skip nodes whose values are equals to head
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                prev.next = head.next;
            } else {
                prev = prev.next;
            }
            head = head.next;
        }

        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode next(ListNode next) {
            this.next = next;
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
