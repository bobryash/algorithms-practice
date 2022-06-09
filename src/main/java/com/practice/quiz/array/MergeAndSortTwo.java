package com.practice.quiz.array;

import java.util.Arrays;

public class MergeAndSortTwo {

    // Merge arr1[0..n1-1] and arr2[0..n2-1]
    // into arr3[0..n1+n2-1]
    public static void mergeArrays(int[] arr1, int[] arr2,
                                   int n1, int n2, int[] arr3) {
        // i - 1 array, j - 2, k - 3
        int i = 0, j = 0, k = 0;

        // Traverse both array
        while (i < n1 && j < n2) {
            // Check if current element of first
            // array is smaller than current element
            // of second array. If yes, store first
            // array element and increment first array
            // index. Otherwise do same with second array
            if (arr1[i] < arr2[j]) {
                arr3[k++] = arr1[i++];
            } else {
                arr3[k++] = arr2[j++];
            }
        }

        // Store remaining elements of first array
        while (i < n1) {
            arr3[k++] = arr1[i++];
        }

        // Store remaining elements of second array
        while (j < n2) {
            arr3[k++] = arr2[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 5, 6};
        int[] arr2 = {4, 7, 8};
        int[] arr3 = new int[arr1.length + arr2.length];

        mergeArrays(arr1, arr2, arr1.length, arr2.length, arr3);
        // System.out.println(Arrays.toString(arr3));
        doIt();
    }

    public static void doIt() {

    }

}
