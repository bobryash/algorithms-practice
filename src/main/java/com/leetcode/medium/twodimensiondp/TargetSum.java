package com.leetcode.medium.twodimensiondp;

import java.util.HashMap;
import java.util.Map;

/**
 * #494. Target Sum
 *
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-'
 * before each integer in nums and then concatenate all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-'
 * before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 *
 * Example 1:
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * Example 2:
 * Input: nums = [1], target = 1
 * Output: 1
 */
public class TargetSum {

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3)); // 5
        System.out.println(findTargetSumWays(new int[]{1}, 1)); // 1
    }

    // the idea - backtracking decision tree with dp cache.
    // 2 decisions - to add or to subtract value from current sum.
    // cache intermediate result in dp
    public static int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> dp = new HashMap<>();
        return backtrack(nums, target, dp, 0, 0);
    }

    private static int backtrack(int[] nums, int target, Map<String, Integer> dp, int i, int sum) {
        if (i >= nums.length) { // base case - no more values in array
            return sum == target ? 1 : 0; // did we manage to get the target?
        }

        String key = i + ", " + sum; // key for intermediate result: current index and needed sum
        if (dp.containsKey(key)) { // e.g. key: array index 3, needed sum 3
            return dp.get(key); // number of ways to get this sum - 2
        }

        // number of different expressions that you can build, which evaluates to current needed sum
        int value = backtrack(nums, target, dp, i + 1, sum + nums[i]) // + cur array value
                + backtrack(nums, target, dp, i + 1, sum - nums[i]); // - cur array value

        dp.put(key, value); // it wasn't in the map, so put a new entry

        return dp.get(key); // return calculated result
    }
}
