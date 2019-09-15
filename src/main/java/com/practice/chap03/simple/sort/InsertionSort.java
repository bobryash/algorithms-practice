package com.practice.chap03.simple.sort;

public class InsertionSort extends BaseSorter {
    public InsertionSort(int a) {
        super(a);
    }

    private void insertionSort() {
        int in, out;
        for (out = 1; out < elemN; out++) { // out - разделительный маркер
            int temp = array[out]; // Скопировать помеченный элемент
            in = out; // Начать перемещения с out
            while (in > 0 && array[in - 1] >= temp) { // Пока не найден меньший элемент
                array[in] = array[in - 1]; // Сдвинуть элемент вправо
                --in; // Перейти на одну позицию влево
            }
            array[in] = temp; // Вставить помеченный элемент
        }
    }

    private void recreIns() {
        int in, out;
        for (out = 1; out < elemN; out++) {
            int temp = array[out];
            in = out;
            while (in > 0 && array[in - 1] >= temp) {
                array[in] = array[in - 1];
                in--;
            }
            array[in] = temp;
        }
    }

    public static void main(String[] args) {
        InsertionSort myInsertionSort = new InsertionSort(10);
        myInsertionSort.insert(10);
        myInsertionSort.insert(4);
        myInsertionSort.insert(1);
        myInsertionSort.insert(3);
        myInsertionSort.insert(34);
        myInsertionSort.insert(8);

        System.out.println("Before");
        myInsertionSort.display();

        myInsertionSort.insertionSort();

        System.out.println("After");
        myInsertionSort.display();
    }

}
