package com.util;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode setNext(ListNode next) {
        this.next = next;
        return this.next;
    }

    public static ListNode getListOfSize(int size) {
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for (int i = 0; i < size - 1; i++) {
            curr.setNext(new ListNode(i + 2));
            curr = curr.next;
        }
        return head;
    }

    @Override
    public String toString() {
        if (this.next == null) {
            return Integer.toString(val);
        }

        StringBuilder builder = new StringBuilder();
        ListNode temp = this;
        while (temp != null) {
            builder.append(temp.val).append(", ");
            temp = temp.next;
        }
        String result = builder.toString();
        return result.substring(0, result.lastIndexOf(", "));
    }
}
