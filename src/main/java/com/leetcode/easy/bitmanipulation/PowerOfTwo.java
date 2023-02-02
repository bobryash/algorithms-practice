package com.leetcode.easy.bitmanipulation;

/**
 * #231
 * <p>
 * Given an integer n, return true if it is a power of two. Otherwise, return false.
 * <p>
 * An integer n is a power of two, if there exists an integer x such that n == 2x.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: true
 * Explanation: 2^0 = 1
 * Example 2:
 * <p>
 * Input: n = 16
 * Output: true
 * Explanation: 2^4 = 16
 * Example 3:
 * <p>
 * Input: n = 3
 * Output: false
 */
public class PowerOfTwo {


    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(1)); // true
        System.out.println(isPowerOfTwo(2)); // true
        System.out.println(isPowerOfTwo(3)); // false
        System.out.println(isPowerOfTwo(4)); // true
    }

    // the idea is that a power of two in binary form has and only has one "1".
    // 1  1
    // 2  10
    // 4  100
    // 8  1000
    // 16 10000
    // 32 100000
    // 64 1000000
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }
}
