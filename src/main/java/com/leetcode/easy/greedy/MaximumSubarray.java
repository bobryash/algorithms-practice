package com.leetcode.easy.greedy;

/**
 * #53. Maximum Subarray
 *
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); // 6
        System.out.println(maxSubArray(new int[]{1})); // 1
        System.out.println(maxSubArray(new int[]{5,4,-1,7,8})); // 23
    }

    // Kadane’s Algorithm
    public static int maxSubArray(int[] nums) {
        int curSum = 0;
        int totalSum = Integer.MIN_VALUE;

        for (int num : nums) {
            curSum = curSum + num;
            if (curSum > totalSum) {
                totalSum = curSum;
            }

            if (curSum < 0) {
                curSum = 0;
            }
        }

        return totalSum;
    }
}
