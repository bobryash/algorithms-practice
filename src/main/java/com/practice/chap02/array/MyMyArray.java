package com.practice.chap02.array;

public class MyMyArray {

    private int[] array;
    private int elementN;

    public MyMyArray(int length) {
        array = new int[length];
        elementN = 0;
    }

    void insert(int v) {
        array[elementN] = v;
        elementN++;
    }

    boolean find(int v) {
        for (int i = 0; i < elementN; i++) {
            if (array[i] == v) {
                return true;
            }
        }
        return false;
    }

    boolean delete(int v) {
        for(int i = 0; i < elementN; i++) {
            if (array[i] == v) {
                for (int j = i; j < elementN; j++) {
                    array[j] = array[j + 1];
                }
                return true;
            }
        }
        return false;
    }

    void display() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < elementN; i++) {
            builder.append(array[i]).append(" ");
        }
        System.out.println(builder);
    }

    void fillArray(int length) {
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
    }

    public static void main(String[] args) {
        MyMyArray array = new MyMyArray(7);
        array.insert(1);
        array.insert(2);
        array.insert(3);
        array.display();
        System.out.println("Delete 2");
        array.delete(2);
        array.display();
        System.out.println("Find 3");
        System.out.println(array.find(3));
    }
}
