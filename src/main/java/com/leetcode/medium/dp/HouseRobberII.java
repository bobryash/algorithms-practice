package com.leetcode.medium.dp;

/**
 * #213. House Robber II
 *
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 * Example 1:
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 *
 * Example 2:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 3:
 * Input: nums = [1,2,3]
 * Output: 3
 */
public class HouseRobberII {

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 3, 2})); // 3
        System.out.println(rob(new int[]{1, 2, 3, 1})); // 4
        System.out.println(rob(new int[]{1, 2, 3})); // 3
        System.out.println(rob(new int[]{1})); // 1
        System.out.println(rob(new int[0]));
    }

    // the idea - to reuse solution from HRI #198, but to apply it to different subsets,
    // (since it's a circle, and you can't rob last house, if you rob first):
    // 1. either start with first (0) and exclude last.
    // 2. or start with second (1) and go until end.
    // compute max rob amount for each subset with solution from HR I.
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        return Math.max(robHelper(nums, 0, nums.length - 1),
                robHelper(nums, 1, nums.length));
    }

    // solution from HRI #198
    private static int robHelper(int[] nums, int start, int end) {
        // sum1 = max of everything to the left, except adjusted house (can rob current and sum with sum1)
        // sum2 = max of everything to the left, including adjusted house (can't rob current if chosen)
        int sum1 = 0, sum2 = 0; // 0 0 1, 2, 3, 4 -> 1, 2, 4, 4 (result)
        for (int i = start; i < end; i++) {
            int temp = sum2;
            sum2 = Math.max(nums[i] + sum1, sum2);
            sum1 = temp;
        }

        return sum2;
    }
}
