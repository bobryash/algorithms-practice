package com.leetcode.medium.dp;

/**
 * #198
 * <p>
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them
 * is that adjacent houses have security systems connected and it will automatically contact the police if two
 * adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money
 * you can rob tonight without alerting the police.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber {

    public static void main(String[] args) {
        int result = new HouseRobber().rob(new int[]{2, 7, 9, 3, 1});

        System.out.println(result);
    }

    // Example Input :         1, 3, 4, 2, 5,  7,  2, 3
    // ----------------------------------------
    // rob:                    1, 3, 5, 5, 10, 12, 12, 15
    // not rob:                0, 1, 3, 5, 5,  10, 12, 12
    // ----------------------------------------

    // input    2 7 9  3  1
    // rob      2 7 11 10 12
    // not rob  0 2 7  11 11
    public int rob(int[] nums) {
        int rob = 0; // max money can get if rob current house
        int notRob = 0; // max money can get if not rob current house

        for (int num : nums) {
            int curRob = notRob + num; // if rob current value, previous house must not be robbed (hence add to prev not rob value)
            notRob = Math.max(notRob, rob); // if not rob then choose max from the previous iteration
            rob = curRob;
        }

        return Math.max(rob, notRob);
    }
}
