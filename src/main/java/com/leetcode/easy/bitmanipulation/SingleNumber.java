package com.leetcode.easy.bitmanipulation;

/**
 * #136
 * <p>
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 * <p>
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: 1
 */
public class SingleNumber {

    public static void main(String[] args) {
        //System.out.println(singleNumber(new int[]{2, 2, 1})); // 1
        //System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2})); // 4
        //System.out.println(singleNumber(new int[]{1})); // 1
        System.out.println(singleNumber(new int[]{2, 4, 5, 2, 3, 4, 3})); // 5
    }

    // The ^ is a bitwise XOR operator. It works like this:
    // 00010 ^ 00010 = 00000;
    // 00000 ^ 10101 = 10101;
    //
    // In other words, if two integers of the same value (and bit representation) are XORed together,
    // the expression evaluates to all 0s.
    //
    // If a number is XORed with all 0s, the expression evaluates to the number itself.
    // These are both properties of the XOR function.
    //
    // XORing the entire array together will eliminate all the duplicates (they will all be set to 0)
    // and leave only the single number.

    // also:
    // a^a=0
    // a^0=a

    // 2 4 5 2 3 4 3
    //
    // res = 0 ^ 2 = 2
    // res = 2 ^ 4
    // res = 2 ^ 4 ^ 5
    // res = 2 ^ 4 ^ 5 ^ 2 = 4 ^ 5
    // res = 4 ^ 5 ^ 3
    // res = 4 ^ 5 ^ 3 ^ 4 = 5 ^ 3
    // res = 5 ^ 3 ^ 3 = 5
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }
}
