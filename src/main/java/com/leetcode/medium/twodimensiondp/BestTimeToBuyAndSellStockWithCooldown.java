package com.leetcode.medium.twodimensiondp;

import java.util.HashMap;
import java.util.Map;

/**
 * #309. Best Time to Buy and Sell Stock with Cooldown
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 * Example 1:
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 * Example 2:
 * Input: prices = [1]
 * Output: 0
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 2, 3, 0, 2})); // 3
        System.out.println(maxProfit(new int[]{1})); // 0
    }

    public static int maxProfit(int[] prices) {
        // start with buying == true, since we must buy before sell
        return dfs(prices, new HashMap<>(), 0, true);
    }

    // rules:
    // - can't sell before buy (and can't just do buy/sell repeatedly).
    // - if you buy, HAVE to do at least one cooldown.
    // - always can choose cooldown instead of buy/sell.
    // the idea - we have a lot of choices (sell/buy/cooldown), so use dp:
    // backtracking with memoization (it's 2D dp because we have 2 dimensions: index and state(buy/sell)
    private static int dfs(int[] prices, Map<String, Integer> cache, int index, boolean buying) {
        // base case - no more stock, no more deals - return 0
        if (index >= prices.length) {
            return 0;
        }

        // calculate and save max value for each combination of stock index and decision (buy/sell)
        String key = index + " " + buying;
        // we might've already calculated value for this key
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (buying) { // if we're buying then we have two choices next: sell or cooldown
            // give me the max value from the branch, where I don't buy next, and sub the current value (since we buy)
            int buy = dfs(prices, cache, index + 1, !buying) - prices[index]; // negation here - go to another state
            // and give me the max value from the branch, where we cooldown (basically skip the choice, proceed to the next stock)
            // we're still buying, so no negation for boolean
            int cooldown = dfs(prices, cache, index + 1, buying);
            // and choose the max value from these choices
            cache.put(key, Math.max(buy, cooldown));
        } else { // if we're selling, first, we have to do a cooldown (so +2 instead of +1), and then 2 choices again:
            // give me max from the branch where I do skip one stock for a cooldown and I sell next
            int sell = dfs(prices, cache, index + 2, !buying) + prices[index]; // negation here - go to another state
            // and max where I do nothing and just do cooldown (no negation again)
            int cooldown = dfs(prices, cache, index + 1, buying);
            // again, choose max from these choices
            cache.put(key, Math.max(sell, cooldown));
        }

        return cache.get(key);
    }
}
