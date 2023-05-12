package com.leetcode.easy;

import java.util.Arrays;

/**
 * #66. Plus One
 *
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
 *
 * Increment the large integer by one and return the resulting array of digits.
 *
 *
 * Example 1:
 *
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Incrementing by one gives 123 + 1 = 124.
 * Thus, the result should be [1,2,4].
 * Example 2:
 *
 * Input: digits = [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 * Incrementing by one gives 4321 + 1 = 4322.
 * Thus, the result should be [4,3,2,2].
 * Example 3:
 *
 * Input: digits = [9]
 * Output: [1,0]
 * Explanation: The array represents the integer 9.
 * Incrementing by one gives 9 + 1 = 10.
 * Thus, the result should be [1,0].
 */
public class PlusOne {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{9, 8}))); // 9 9
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9}))); // 1 0 0 0
        System.out.println(Arrays.toString(plusOne(new int[]{1, 9, 9, 2, 9}))); // 1 9 9 3 0
    }

    /**
     *  The algorithm starts from the last digit of the array, and if it is less than 9,
     *  it simply increments it and returns the array. If the last digit is 9,
     *  it sets it to 0 and continues to the next digit to increment it.
     *  This process continues until a digit that is less than 9 is found.
     *
     * If all the digits are 9, the algorithm creates a new array with one additional
     * element and sets its first element to 1.
     * This handles the special case when the input is 999...999, and the output should be 1000...000.
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] ==  9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }

        // if we got here, then every number was 9, means that original number is going to expand by one index (99 -> 100)
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
