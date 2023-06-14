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

    public ListNode next(ListNode node) {
        this.next = node;
        return this;
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

    private void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode curr = this;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        return sb.toString();
    }
}
