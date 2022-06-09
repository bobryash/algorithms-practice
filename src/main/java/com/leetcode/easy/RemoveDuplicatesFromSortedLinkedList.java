package com.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class RemoveDuplicatesFromSortedLinkedList {

    public static void main(String[] args) {
        new RemoveDuplicatesFromSortedLinkedList().test();
    }

    private void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);

        System.out.println(deleteDuplicates(head));
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode list = head; // используем одну новую ссылку. оригинальная ссылка смотрит на ориг объект
        while (list.next != null) {
            if (list.val == list.next.val)
                list.next = list.next.next;
            else
                list = list.next;
        }
        return head;
    }

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
