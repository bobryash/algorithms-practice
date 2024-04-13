package com.leetcode.medium.dp;

/**
 * #198. House Robber
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
        System.out.println(rob(new int[]{1, 2, 3, 1})); // 4
        System.out.println(rob(new int[]{2, 7, 9, 3, 1})); // 12
    }

    // Example Input :         1, 3, 4, 2, 5,  7,  2, 3
    // ----------------------------------------
    // rob:                    1, 3, 5, 5, 10, 12, 12, 15
    // not rob:                0, 1, 3, 5, 5,  10, 12, 12
    // ----------------------------------------

    // input    2 7 9  3  1
    // rob      2 7 11 10 12
    // not rob  0 2 7  11 11

    // the idea - for each house, you choose max of these values
    // (which basically represent 2 choices: rob or not to rob current house):
    // 1. current house rob value + rob max of every house to the left, EXCEPT adjusted one (can't rob it).
    // 2. value of an adjusted house to left, which represent current rob max amount of ALL houses to the left
    // (which is already calculated) (and doesn't include (rob) current house value)
    // incrementally build the result from left to right, solving sub-problems,
    // staring with base case (rob/not rob first house), store these sums in two variables.
    // dp breaks it into sub-problems: rob = max(houses[0] + rob[2..n], rob[1...n]) - each rob is sub-problem
    public static int rob(int[] nums) {
        // for input 1 2 3 1, i = 3
        // robAndPlusWithCurrent - sum of everything to the left, except adjusted house:[1(robAndPlusWithCurrent),2,3(i), 4]
        // dontRobAndTakeCurMax - sum of everything to the left, except adjusted house:[1,2(dontRobAndTakeCurMax),3(i), 4]
        int robAndPlusWithCurrent = 0, dontRobAndTakeCurMax = 0; // 0 0 1, 2, 3, 4 -> 1, 2, 4, 4 (result)

        for (int current: nums) {
            int temp = dontRobAndTakeCurMax;
            dontRobAndTakeCurMax = Math.max(current + robAndPlusWithCurrent, dontRobAndTakeCurMax);
            robAndPlusWithCurrent = temp;
        }

        return dontRobAndTakeCurMax;
    }
}
