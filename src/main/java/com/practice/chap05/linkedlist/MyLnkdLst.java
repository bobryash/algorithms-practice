package com.practice.chap05.linkedlist;

public class MyLnkdLst {

    Link first;
    Link last;

    boolean isEmpty() {
        return first == null;
    }

    public void addFirst(int data) {

    }

    public void addLast(int data) {

    }

    public Link removeFirst() {
       return null;
    }

    public Link removeLast() {
        return null;
    }

    public Link deleteKey(int key) {
        return null;
    }

    void display() {
        Link current = first;
        StringBuilder builder = new StringBuilder();
        while (current != null) {
            builder.append(current.data).append(" ");
            current = current.next;
        }
        System.out.println(builder);
    }

    class Link {
        int data;
        Link next;
        Link previous;

        public Link(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        MyLnkdLst myLnkdLst = new MyLnkdLst();
        myLnkdLst.addFirst(1);
        myLnkdLst.addFirst(2);
        myLnkdLst.addFirst(3);
        myLnkdLst.addFirst(4);
        myLnkdLst.addFirst(5);
        myLnkdLst.addFirst(6);

        System.out.println("Before");
        myLnkdLst.display();

        myLnkdLst.removeFirst();
        System.out.println("First delete");
        myLnkdLst.display();

        myLnkdLst.removeLast();
        System.out.println("Last delete");
        myLnkdLst.display();

        myLnkdLst.deleteKey(3);
        System.out.println("Delete 3");
        myLnkdLst.display();

    }
}
