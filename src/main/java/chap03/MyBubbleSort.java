package chap03;

public class MyBubbleSort {

    private int[] array;
    private int elemN;

    private MyBubbleSort(int a) {
        array = new int[a];
        elemN = 0;
    }

    /**
     * Поочередно сравниваются два близстоящих элемента.
     * После каждого прохода крайняя пара всегда отсортирована правильно.
     * С каждым проходом правый край сдвигается (outer)
     * сложность алгоритма - O(N^2) - потому что вложенные циклы - bad
     */
    private void bubbleSort() {
        for (int i = elemN - 1; i > 0; i--) { // outer
            for (int j = 0; j < i; j++) { // inner
                if (array[j] > array[j + 1]) { // if left is bigger than right
                    // swap elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private void insert(int v) {
        array[elemN] = v;
        elemN++;
    }

    private void display() {
        for (int i = 0; i <= elemN - 1; i++) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        MyBubbleSort myBubbleSort = new MyBubbleSort(10);
        myBubbleSort.insert(10);
        myBubbleSort.insert(4);
        myBubbleSort.insert(1);
        myBubbleSort.insert(3);
        myBubbleSort.insert(34);
        myBubbleSort.insert(8);

        System.out.println("Before");
        myBubbleSort.display();

        myBubbleSort.bubbleSort();

        System.out.println("After");
        myBubbleSort.display();

    }
}
