package com.leetcode.medium.dp;

import java.util.Arrays;

/**
 * #322. Coin Change
 *
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 */
public class CoinChange {

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11)); // 3
        System.out.println(coinChange(new int[]{2}, 3)); // -1
        System.out.println(coinChange(new int[]{1}, 0)); // 0
    }

    // the idea - use DP, break problem into sub-problems, build solution from left to right (0...amount):
    // create dp(minCoins) array to store minimum coins number for each amount (0...amount).
    // by default set amount which is bigger than amount in input - to set by default that it's not possible to make that amount
    // pre-set base case - 0 coins needed to sum up to amount 0.
    // for each amount, go through each coin and try to find min num of coins
    // (by using coin value and searching for leftover amount in precalculated indexes)
    public static int coinChange(int[] coins, int amount) {
        // Initialize a variable to store the minimum number of coins needed for each amount
        int[] minCoins = new int[amount + 1]; // +1 to make room for 0 amount
        // Initialize all elements in minCoins to 'amount' + 1 to indicate that it's not possible to make that amount
        Arrays.fill(minCoins, amount + 1);

        // Base case: 0 coins are needed to make amount 0
        minCoins[0] = 0;

        // Iterate through all amounts from 1 to 'amount'
        for (int i = 1; i <= amount; i++) { // i = 1 to skip 0. i is current amount here
            // Try each coin denomination
            for (int coin: coins) {
                // If the current coin denomination can be used to make the current amount
                if (coin <= i) {
                    // Update minCoins[i] by taking the minimum between its current value and minCoins[i - coin] + 1
                    // minCoins[i - coin] + 1: total number of coins we need to have, if we use current coin:
                    // minCoins[i - coin] - try to find already calculated num of coins for amount without current coin value
                    // + 1 - current used coin (as a number of coins, not amount!)
                    minCoins[i] = Math.min(minCoins[i], minCoins[i - coin] + 1);
                }
            }
        }

        // If minCoins[amount] is still greater than 'amount', it means it's not possible to make the amount with the given coins
        return minCoins[amount] > amount ? -1 : minCoins[amount];
    }
}
