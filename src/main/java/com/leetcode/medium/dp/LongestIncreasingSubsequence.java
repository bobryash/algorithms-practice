package com.leetcode.medium.dp;

import java.util.Arrays;

/**
 * #300. Longest Increasing Subsequence
 *
 * Given an integer array nums, return the length of the longest strictly increasing
 * subsequence
 * .
 *
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 4
        System.out.println(lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3})); // 4
        System.out.println(lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7})); // 1
    }

    // the idea - classic dp
    // initiate a dp array with default value 1 (1num = 1length seq)
    // then for each next value, go through each previous value,
    // and if current one is bigger than previous (to create a proper seq [2,3], not [3,2])
    // choose to update dp[i] by current value dp[i] or sum of taking cur num (1) + whatever is pre-calculated in dp[j]
    public static int lengthOfLIS(int[] nums) {
        int[] lts = new int[nums.length]; // dp[]
        Arrays.fill(lts, 1); // by default: 1 num = 1 length sequence

        int maxLength = 1; // read why below

        // Iterate through each number starting from the second one
        // (start with 1, skipping base case nums[0] = 1 (1num = 1 length seq))
        for (int i = 1; i < nums.length; i++) {
            // Check each previous number to find the longest increasing subsequence
            for (int j = 0; j < i; j++) {
                // only if current number is more than previous one, to create correct sequense [2, 3], not [3, 2]
                if (nums[i] > nums[j]) {
                    // we use lts[i] instead of just 1, because it could be updated each cycle, so use current val.
                    // lts[j] + 1 means what if we use current num and take precalculated max seq length at lts[j].
                    lts[i] = Math.max(lts[i], lts[j] + 1);
                }

                // need to cache the max result in a separate var, because lts[i] doesn't always go up
                // like in [10,9,2,5,3,7,101,18(here)]
                maxLength = Math.max(maxLength, lts[i]);
            }
        }

        return maxLength;
    }
}
