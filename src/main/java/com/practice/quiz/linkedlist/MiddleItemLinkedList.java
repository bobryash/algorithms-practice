package com.practice.quiz.linkedlist;

import com.practice.chap05.linkedlist.DoublyLinkedList;
import com.practice.chap05.linkedlist.DoublyLinkedList.Link;

/**
 * Java program to find middle element of linked list in one pass.
 * In order to find middle element of a linked list
 * we need to find the length first but since we can only
 * traverse linked list one time, we will have to use two pointers
 * one which we will increment on each iteration while
 * other which will be incremented every second iteration.
 * So when the first pointer will point to the end of a
 * linked list, second will be pointing to the middle
 * element of a linked list
 *
 */
public class MiddleItemLinkedList {

  public static void main(String args[]) {
    //creating LinkedList with 5 elements including head
    DoublyLinkedList linkedList = getList(13);
    //finding middle element of LinkedList in single pass
    Link current = linkedList.first;
    Link middle = linkedList.first;
    int length = 0;

    while (current.next != null) {
      length++;
      if (length % 2 == 0) {
        middle = middle.next;
      }
      current = current.next;
    }

    if (length % 2 == 1) {
      middle = middle.next;
    }

    System.out.println("length of LinkedList: " + length);
    linkedList.displayForward();
    System.out.println("middle element of LinkedList : " + middle.dData);
  }

  static DoublyLinkedList getList(int length) {
    DoublyLinkedList linkedList = new DoublyLinkedList();
    for (int i = 0; i < length; i++) {
      linkedList.addFirst((int) (Math.random() * 10));
    }
    return linkedList;
  }
}
