package com.leetcode.medium.dp;

/**
 * #152. Maximum Product Subarray
 *
 * Given an integer array nums, find a subarray
 * that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * Example 1:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 *
 * Example 2:
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-1, -2, -3})); // 6
        System.out.println(maxProduct(new int[]{2, 3, -2, 4})); // 6
        System.out.println(maxProduct(new int[]{-2, 0, -1})); // 0
        System.out.println(maxProduct(new int[]{-2, 3, 0, -1})); // 3
    }

    // goal - to find max product, which will be produced by some subarray in a given array (or maybe by entire array).
    // the idea - use dp, break problem into sub-problems, go from left to right:
    // to find max product for last value [0...n], we need to first calculate max for [0...n-1], etc..
    // start with 0, assign current max and min to nums[0], then keep updating max, min and result.
    // negative values are the biggest catch (otherwise can just keep updating max num by num):
    // you need to keep track of min, in addition to max, because negative min (-48) can give
    // positive (and possible result) product with some negative num (-2) further the array:
    // -2 * (-48) = +96
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // start with base case - index 0
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // use temp var for max, so it won't change after new max calculation.
            // we need to use same (current) min and max values in both calcs.
            // no minTemp, because min is not used in cycle after its calculation
            int tempMax = max;
            max = Math.max(nums[i], Math.max(nums[i] * tempMax, nums[i] * min));
            min = Math.min(nums[i], Math.min(nums[i] * tempMax, nums[i] * min));

            result = Math.max(result, max);
        }

        return result;
    }
}
