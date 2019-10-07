package com.practice.quiz.linkedlist;

import com.practice.chap05.linkedlist.DoublyLinkedList;

public class NNodeFromTheEndLinkedList {

    /**
     * to get the nth node from end * * @param n * @return nth node from last
     * Смысл в том, чтобы установить расстояние между указателями в n
     * Сначала двигается только первый, потом, когда расстояние становится n -
     * - двигаются оба. когда первый достигает конца списка, второй указывает
     * на элемне по адресу 'n от конца'
     */
    public static long getLastNode(DoublyLinkedList linkedList, int n) {
        DoublyLinkedList.Link fast = linkedList.first;
        DoublyLinkedList.Link slow = linkedList.first;
        int start = 1;
        while (fast.next != null) {
            fast = fast.next;
            start++;
            if (start > n) {
                slow = slow.next;
            }
        }
        return slow.dData;
    }

    public static  long hhh(DoublyLinkedList linkedList, int n) {
        return 1;
    }

    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.addFirst(76);
        linkedList.addFirst(57);
        linkedList.addFirst(455);
        linkedList.addFirst(98);
        linkedList.addFirst(7);

        linkedList.displayForward();
        System.out.println(hhh(linkedList, 3));
    }
}
