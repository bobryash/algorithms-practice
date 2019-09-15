package com.practice.chap03.simple.sort;

public class BaseSorter {

    protected int[] array;
    protected int elemN;

    public BaseSorter(int a) {
        array = new int[a];
        elemN = 0;
    }

    protected void insert(int v) {
        array[elemN] = v;
        elemN++;
    }

    protected void display() {
        for (int i = 0; i <= elemN - 1; i++) {
            System.out.println(array[i]);
        }
    }

    void fillArray(int maxSize) {
        for (int j = 0; j < maxSize; j++) // Заполнение массива
        { // случайными числами
            int n = (int) (java.lang.Math.random() * (maxSize - 1));
            insert(n);
        }
    }
}

