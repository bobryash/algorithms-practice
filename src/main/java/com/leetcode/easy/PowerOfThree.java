package com.leetcode.easy;

/**
 * #326. Power of Three
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 * <p>
 * An integer n is a power of three, if there exists an integer x such that n == 3^x.
 * <p>
 * Example 1:
 * Input: n = 27
 * Output: true
 * Explanation: 27 = 3^3
 * <p>
 * Example 2:
 * Input: n = 0
 * Output: false
 * Explanation: There is no x where 3^x = 0.
 * <p>
 * Example 3:
 * Input: n = -1
 * Output: false
 * Explanation: There is no x where 3^x = (-1).
 *
 */
public class PowerOfThree {

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(27)); // true
        System.out.println(isPowerOfThree(2)); // false
        System.out.println(isPowerOfThree(0)); // false
        System.out.println(isPowerOfThree(-1)); // false
    }

    public static boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}
