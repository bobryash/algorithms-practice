package com.practice.chap05.linkedlist;

public class MyMyLinkedList {

    public static void main(String[] args) {
        MyMyLinkedList linkedList = new MyMyLinkedList();
        linkedList.insert(11);
        linkedList.insert(12);
        System.out.println(linkedList);

        linkedList.insertFirst(10);
        System.out.println(linkedList);

        linkedList.insert(1, 9);
        System.out.println(linkedList);
    }

    private Node head;

    public void insert(int data) {
        Node newNode = new Node(data);
        if (this.head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.setNext(newNode);
        }
    }

    // 10 -> 11 -> 12 -----> 10 -> 9 -> 11 -> 12
    public void insert(int index, int value) {
        Node toInsert = new Node(value);
        Node node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        toInsert.setNext(node.next);
        node.setNext(toInsert);
    }

    public void insertFirst(int data) {
        Node newNode = new Node(data);
        newNode.setNext(this.head);
        this.head = newNode;
    }

    private class Node {
        private int data;
        private Node next;

        Node(int data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return "";
        }

        if (this.head.next == null) {
            return Integer.toString(head.data);
        }

        StringBuilder result = new StringBuilder();
        Node current = this.head;
        while (current.next != null) {
            result.append(current.data).append(" ");
            current = current.next;
        }

        return result.append(current.data).toString();
    }
}
