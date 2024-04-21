package com.leetcode.easy.dp;

/**
 * #746. Min Cost Climbing Stairs
 *
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
 * Once you pay the cost, you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 *
 *
 * Example 1:
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 *
 * Example 2:
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20})); // 15
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1})); // 6
    }

    // the idea - DP, go from bottom to top, from right to left, build solution
    // for input [10, 15, 20]
    // cost (one step, two steps):
    // for 20 + min(0, 0) = 20
    // for 15 + min(20, 0) = 15
    // for 10 + min(15, 20) = 25
    // result = min(15, 25) = 15
    // N.B. - top of a ladder here is the index BEYOND array: [10, 15, 20] null(this!)
    public static int minCostClimbingStairs(int[] cost) {
        int one = 0;
        int two = 0;

        for (int i = cost.length - 1; i >= 0; i--) {
            cost[i] = cost[i] + Math.min(one, two);
            two = one;
            one = cost[i];
        }

        return Math.min(cost[0], cost[1]);
    }

    public static int minCostClimbingStairs2(int[] cost) {
        // +1 to make space for the base case, top of the ladder, which cost is 0
        // [10, 15, 20] null(this!)
        int[] dp = new int[cost.length + 1];
        // base cases
        dp[0] = 0; // it's defaulted to 0 already, but to make it explicit
        dp[1] = cost[0]; // can do only one step to the top, from step right before the top

        // each intermediate result will be the result of the previously calculated results,
        // calculation starts will base cases.
        // here start with i=2, because we have i=0 and i=1 calculated (base cases)
        for (int i = 2; i < dp.length; i++) {
            // dp array is bigger than cost by 1, so do -1
            dp[i] = Math.min(cost[i - 1] + dp[i - 1], cost[i - 1] + dp[i - 2]);
        }

        // since you can start either from 1 or 2 step
        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }
}
