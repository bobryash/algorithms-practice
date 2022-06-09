package com.practice.quiz.linkedlist;

import com.practice.chap05.linkedlist.DoublyLinkedList;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesLinkedList {

    public static void deleteDups(DoublyLinkedList.Link link) {
        Set set = new HashSet();
        DoublyLinkedList.Link previous = null;
        while (link != null) {
            if (set.contains(link.dData)) {
                previous.next = link.next;
            } else {
                set.add(link.dData);
                previous = link;
            }
            link = link.next;
        }
    }

    /*
     * since its a sorted list, we can just compare consecutive elements to find
     * the duplicates
     */
    public void removeDuplicates(DoublyLinkedList.Link head) {
        DoublyLinkedList.Link current = head;

        while (current != null && current.next != null) {
            if (current.dData == current.next.dData) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
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
