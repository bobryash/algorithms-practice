package com.leetcode;

import java.util.Arrays;

public class MergeTwoSortedArrays {

    public static void main(String[] args) {
        int[] res = merge(new int[]{1, 3, 5}, new int[]{2, 8, 9});

        System.out.println(Arrays.toString(res)); // 1 2 3 5 8 9
    }

    static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];

        int resCount = 0, countA = 0, countB = 0;

        while (countA < a.length && countB < b.length) {
            if (a[countA] < b[countB]) {
                result[resCount] = a[countA];
                countA++;
            } else {
                result[resCount] = b[countB];
                countB++;
            }
            resCount++;
        }

        while (countA < a.length) {
            result[resCount] = a[countA];
            resCount++;
            countA++;
        }

        while (countB < b.length) {
            result[resCount] = b[countB];
            resCount++;
            countB++;
        }

        return result;
    }
}
