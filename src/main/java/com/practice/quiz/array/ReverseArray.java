package com.practice.quiz.array;

import java.util.Arrays;

public class ReverseArray {

    public static void main(String[] args) {
        int[] arr = getArray();
        System.out.println(Arrays.toString(arr));

        myReverse(arr);

        System.out.println(Arrays.toString(arr));

    }

    private static void invertUsingFor(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    static int[] getArray() {
        return new int[]{1, 2, 3, 3, 4, -2, 8};
    }

    private static void myReverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
