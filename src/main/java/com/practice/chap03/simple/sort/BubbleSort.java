package com.practice.chap03.simple.sort;

public class BubbleSort extends BaseSorter {


    public BubbleSort(int a) {
        super(a);
    }

    /**
     * Поочередно сравниваются два близстоящих элемента.
     * После каждого прохода крайняя пара всегда отсортирована правильно.
     * С каждым проходом правый край сдвигается (outer)
     * сложность алгоритма - O(N^2) - потому что вложенные циклы - bad
     */
    private void bubbleSort() {
        int in, out;
        for (out = elemN - 1; out > 0; out--) { // outer
            for (in = 0; in < out; in++) { // inner
                if (array[in] > array[in + 1]) { // if left is bigger than right
                    // swap elements
                    int temp = array[in];
                    array[in] = array[in + 1];
                    array[in + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {


        BubbleSort bubbleSort = new BubbleSort(10000);
        /*myBubbleSort.insert(10);
        myBubbleSort.insert(4);
        myBubbleSort.insert(1);
        myBubbleSort.insert(3);
        myBubbleSort.insert(34);
        myBubbleSort.insert(8);*/
        bubbleSort.fillArray(10000);

        /*System.out.println("Before");
        myBubbleSort.display();*/

        bubbleSort.bubbleSort();

        System.out.println("After");
        bubbleSort.display();

        bubbleSort.bubbleSort();
    }

}
