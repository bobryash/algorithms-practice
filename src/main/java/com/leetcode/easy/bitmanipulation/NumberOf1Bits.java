package com.leetcode.easy.bitmanipulation;

/**
 * #191
 * <p>
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has
 * (also known as the Hamming weight).
 * <p>
 * Note:
 * <p>
 * Note that in some languages, such as Java, there is no unsigned integer type.
 * In this case, the input will be given as a signed integer type.
 * It should not affect your implementation, as the integer's internal binary representation is the same,
 * whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation.
 * Therefore, in Example 3, the input represents the signed integer. -3.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 00000000000000000000000000001011
 * Output: 3
 * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
 * Example 2:
 * <p>
 * Input: n = 00000000000000000000000010000000
 * Output: 1
 * Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
 * Example 3:
 * <p>
 * Input: n = 11111111111111111111111111111101
 * Output: 31
 * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 */
public class NumberOf1Bits {

    public static void main(String[] args) {
        int result = hammingWeight(00000000000000000000000000001011);

        System.out.println(result); // 3
    }

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        // int : binary
        // 521 : 1000001001
        // 260 : 100000100
        // 130 : 10000010
        // 65  : 1000001
        // 32  : 100000
        // 16  : 10000
        // 8   : 1000
        // 4   : 100
        // 2   : 10
        // 1   : 1
        int ones = 0;
        while (n != 0) {
            ones = ones + (n & 1); // zeros = zeros + ((n & 1) ^ 1); - to count 0
            n = n >>> 1;
        }
        return ones;
    }
}
