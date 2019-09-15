package com.practice.quiz.array;

import java.util.Arrays;

public class ReverseArray {

    public static void main(String[] args) {
        int[] arr = getArray();
        System.out.println(Arrays.toString(arr));

        invertUsingFor(arr);

        System.out.println(Arrays.toString(arr));

    }

    static void invertUsingFor(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    static int[] getArray() {
        return new int[]{1, 2, 3, 3, 4, -2, 8};
    }

}
