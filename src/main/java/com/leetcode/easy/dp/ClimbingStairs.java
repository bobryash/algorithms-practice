package com.leetcode.easy.dp;

/**
 * #70. Climbing Stairs
 * <p>
 * You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(2)); // 2
        System.out.println(climbStairs(3)); // 3
    }

    // the idea - DP. break the problem into sub-problems, go from bottom to top.
    // from right to the left:
    // start with the last step, step to the top is 1.
    // next step (before last), is also only 1 step to the top.
    // next step is sum of previously calculated steps
    // (sum of 2 next, because we have one and two steps options):
    // x -> y (one step) or x ->-> z (two steps),
    // y and z already calculated their ways, so x = y + z,
    // and y = previous value of x (before it updated to y + z)
    // n.. x y z
    // 8 5 3 2 1 1 (from left to right, cycle starts with calculating 2(x in example))
    // (basically a Fibonaccy sequence)
    public static int climbStairs(int n) {
        int one = 1, two = 1;

        // skip the last one, because it's always 1 (top)
        for (int i = 0; i < n - 1; i++) {
            int temp = one;
            one = one + two;
            two = temp;
        }

        return one;
    }

    // with extra memory, idea is the same
    public static int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
