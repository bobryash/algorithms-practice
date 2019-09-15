package com.practice.chap03.simple.sort;

public class SelectionSort extends BaseSorter {
    public SelectionSort(int a) {
        super(a);
    }

    private void selectSort() {
        int minIndex = 0;
        for (int out = 0; out < elemN; out++) {
            for (int in = out + 1; in < elemN; in++) {
                if (array[in] < array[minIndex]) {
                    minIndex = in;
                }
            }
            int temp = array[out];
            array[out] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort(10);
        selectionSort.insert(10);
        selectionSort.insert(4);
        selectionSort.insert(1);
        selectionSort.insert(3);
        selectionSort.insert(34);
        selectionSort.insert(8);

        System.out.println("Before");
        selectionSort.display();

        selectionSort.selectSort();

        System.out.println("After");
        selectionSort.display();
    }

}
