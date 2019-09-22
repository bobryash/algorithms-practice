package com.practice.quiz.linkedlist;

import com.practice.chap05.linkedlist.DoublyLinkedList;

class ReverseLinkedList {

    static void reverseIteratively(DoublyLinkedList list) {
        DoublyLinkedList.Link current = list.first;
        DoublyLinkedList.Link prev = null;
        DoublyLinkedList.Link next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        list.first = prev;
    }

    /**
     * What is the reverse of null (the empty list)? null.
     * What is the reverse of a one element list? the element.
     * What is the reverse of an n element list? the reverse of the rest of the list followed by the first element.
     */
    static DoublyLinkedList.Link reverseRecursively(DoublyLinkedList.Link link) {
        if (link == null) return null; // first question

        if (link.next == null) return link; // second question

        // third question - in Lisp this is easy, but we don't have cons
        // so we grab the second element (which will be the last after we reverse it)
        DoublyLinkedList.Link secondElem = link.next;

        // bug fix - need to unlink list from the rest or you will get a cycle
        link.next = null;

        // then we reverse everything from the second element on
        DoublyLinkedList.Link reverseRest = reverseRecursively(secondElem);

        // then we join the two lists
        secondElem.next = link;

        return reverseRest;
    }

    static void revIter(DoublyLinkedList linkedList) {

    }

    static DoublyLinkedList.Link revRec(DoublyLinkedList.Link link) {
        return null;
    }

    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.addFirst(4);
        linkedList.addFirst(5);

        linkedList.displayForward();

        //linkedList.first = revRec(linkedList.first);
        revIter(linkedList);

        linkedList.displayForward();
    }

}
