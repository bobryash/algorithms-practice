package com.leetcode.medium;

import java.util.Arrays;

/**
 * #238. Product of Array Except Self
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of
 * all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * <p>
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 */
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{-1, 1, 0, -3, 3})));
    }

    //  a b c d
    //  algorithm goes forward and back ---> 0 1 2 3 -> 3 2 1 0
    //  (goes through result array, not nums)
    //  prefix --->              --->
    //  | 1 | a | a * b | a * b * c | (set first vals in result with prefix)
    //  postfix <--- :           <---
    //  |b * c * d |  c * d | d | 1 | (multiply result by postfix, backwards)
    //  result is:
    //  |b * c * d | a * c * d | a * b * d | a * b * c
    //
    //  (1 before and 1 after because no post/pre product for first and last value)
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix;
            prefix = prefix * nums[i];
        }

        int postfix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = postfix * result[i];
            postfix = postfix * nums[i];
        }

        return result;
    }
}
