package com.practice.quiz.linkedlist;

import com.practice.chap05.linkedlist.DoublyLinkedList;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesLinkedList {

    public static void deleteDups(DoublyLinkedList.Link n) {
        Set table = new HashSet();
        DoublyLinkedList.Link previous = null;
        while (n != null) {
            if (table.contains(n.dData)) {
                previous.next = n.next;
            } else {
                table.add(n.dData);
                previous = n;
            }
            n = n.next;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.addFirst(3);

        linkedList.displayForward();
        RemoveDuplicatesLinkedList.deleteDups(linkedList.first);
        linkedList.displayForward();
    }
}
