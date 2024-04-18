package com.leetcode.medium.dp;

/**
 * #416. Partition Equal Subset Sum
 *
 * Given an integer array nums, return true if you can partition the array into two
 * subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 *
 * Example 1:
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5})); // true
        System.out.println(canPartition(new int[]{1, 2, 3, 5})); // false
        System.out.println(canPartition(new int[]{2, 1, 3, 1, 1})); // true
        System.out.println(canPartition(new int[]{3, 3, 2, 4})); // true
    }

    // the idea - dp, try to find nums to sum up them to half of total sum,
    // do it by checking if we can sum up to 0...sum/2
    // meaning pre-calculate dp answers for each of these sums, to build the answer to dp[sum/2]
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If the sum of the array elements is odd, it cannot be partitioned into two equal subsets
        if (sum % 2 != 0) {
            return false;
        }

        // if we nums, which sum up to half of the sum, then other nums also will sum up to sum/2,
        // then answer is true
        int target = sum / 2;
        // array represents whether it's possible to achieve a sum of i using elements from the array.
        // e.g. input [3,3,2,4], sum=12, target=12/2=6
        // dp = [true(base case), false,false,false,false,false]
        // indexes=sums: [0,1,2,3,4,5,6]. each index is the answer - can we sum i from what we have in array?
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // Base case: an empty subset can always achieve a sum of 0

        // for each num in nums we go through index=target to index=num(e.g. from 6 to 2) and ask,
        // can we build i with a current num (6 with 2)? 6 - 2(we used 2),
        // so check what is the answer we pre-calculated (or not yet) for dp[6-2]=dp[4] index?
        // N.B.: and for each sum i, which equals to num we're going to have true by default:
        // i = 2, num = 2, dp[2] = dp[2 - 2] = dp[0] = true (dp[0] - base case!)
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }

        return dp[target];
    }
}
