package com.practice.quiz.array;

import java.util.HashSet;
import java.util.Set;

public class ArraySumOfTwo {

    public static void main(String[] args) {
        ArraySumOfTwo.findPairsBySet(getArray(), 6);
        //ArraySumOfTwo.findPairsByBruteForce(getArray(), 6);
    }

    static void findPairsBySet(int[] array, int sum) {
        Set<Integer> set = new HashSet<>(array.length);
        for (int value : array) {
            int target = sum - value;
            if (!set.contains(target)) {
                set.add(value);
            } else {
                System.out.printf("(%d + %d) = %d", value, target, sum);
                System.out.println();
            }
        }

    }

    static void findPairsByBruteForce(int[] array, int sum) {
        for (int i = 0; i < array.length; i++) {
            int first = array[i];
            for (int j = i + 1; j < array.length; j++) {
                int second = array[j];
                if ((first + second) == sum) {
                    System.out.printf("(%d, %d) %n", first, second);
                }
            }
        }
    }

    static int[] getArray() {
        return new int[]{1, 2, 3, 3, 4, -2, 8};
    }

}
