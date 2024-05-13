package com.leetcode.medium.dp;

/**
 * #518. Coin Change II
 *
 * You are given an integer array coins representing coins of
 * different denominations and an integer amount representing a total amount of money.
 *
 * Return the number of combinations that make up that amount. If that amount of
 * money cannot be made up by any combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 *
 * Example 1:
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * Example 2:
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Example 3:
 * Input: amount = 10, coins = [10]
 * Output: 1
 */
public class CoinChangeII {

    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1, 2, 5})); // 4
        //System.out.println(change(3, new int[]{2})); // 0
        //System.out.println(change(10, new int[]{10})); // 1
    }

    // the idea - dp.
    // we initialize dp[0] to 1 because there is one way to make 0 amount (using no coins).
    // then, for each coin in the coins array, we iterate through the dp[] array starting from the coin value up to the target amount.
    // for each amount i, we update dp[i] by adding the number of ways to make the amount i - coin using the current coin.
    public static int change(int amount, int[] coins) {
        // goal amount = 5, amounts array (0(base case)...amount): [0, 1, 2, 3, 4, 5]
        // each index is intermediate amount.
        // each index's value is the answer to "how many ways to sum up to that amount index".
        int[] dp = new int[amount + 1];
        dp[0] = 1; // base case: 1 way to make 0 amount (using no coins)

        // for each coin: how many ways to sum up each amount with that coin?
        // e.g. only 1 way to sum up each amount with 1: 1; 2: 1+1; 3: 1+1+1;..
        // we kind of build the answer layer by layer, coin by coin, rewriting values in array
        for (int coin: coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
}
