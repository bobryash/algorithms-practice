package com.practice.quiz.linkedlist;

public class CyclicLinkedList {

    public static void main(String args[]) {
        new CyclicLinkedList().notCyclic();
        System.out.println();
        new CyclicLinkedList().cyclic();
    }

    void notCyclic() {
        LinkedList linkedList = new LinkedList();
        linkedList.appendIntoTail(new Node("101"));
        linkedList.appendIntoTail(new Node("201"));
        linkedList.appendIntoTail(new Node("301"));
        linkedList.appendIntoTail(new Node("401"));
        System.out.println("Linked List : " + linkedList);
        if (linkedList.isCyclic()) {
            System.out.println("Linked List is cyclic as it contains cycles or loop");
        } else {
            System.out.println("LinkedList is not cyclic, no loop or cycle found");
        }
    }

    void cyclic() {
        LinkedList linkedList = new LinkedList();
        linkedList.appendIntoTail(new Node("101"));
        Node cycle = new Node("201");
        linkedList.appendIntoTail(cycle);
        linkedList.appendIntoTail(new Node("301"));
        linkedList.appendIntoTail(new Node("401"));
        linkedList.appendIntoTail(cycle); //don't call toString method in case of cyclic linked list, it will throw OutOfMemoryError //System.out.println("Linked List : " + linkedList); if(linkedList.isCyclic()){ System.out.println("Linked List is cyclic as it contains cycles or loop"); }else{ System.out.println("LinkedList is not cyclic, no loop or cycle found"); }

        if (linkedList.isCyclic()) {
            System.out.println("Linked List is cyclic as it contains cycles or loop");
        } else {
            System.out.println("LinkedList is not cyclic, no loop or cycle found");
        }
    }

    public class LinkedList {
        private Node first;

        public LinkedList() {
            this.first = new Node("head");
        }

        public Node head() {
            return first;
        }

        public void appendIntoTail(Node node) {
            Node current = first;

            //find last element of LinkedList i.e. tail
            while (current.next() != null) {
                current = current.next();
            }
            //appending new node to tail in LinkedList
            current.setNext(node);
        }

        /*
         * If singly LinkedList contains Cycle then following would be true
         * 1) slow and fast will point to same node i.e. they meet
         * On the other hand if fast will point to null or next node of
         * fast will point to null then LinkedList does not contains cycle.
         */
        public boolean isCyclic() {
            Node fast = first;
            Node slow = first;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;

                //if fast and slow pointers are meeting then LinkedList is cyclic
                if (fast == slow) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class Node {
        private Node next;
        private String data;

        public Node(String data) {
            this.data = data;
        }

        public Node next() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
