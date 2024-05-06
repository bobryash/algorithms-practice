package com.leetcode.medium.greedy;

import java.util.Map;

/**
 * #45. Jump Game II
 *
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words,
 * if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1].
 * The test cases are generated such that you can reach nums[n - 1].
 *
 *
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 */

public class JumpGameII {

    public static void main(String[] args) {
        System.out.println(jump(new int[]{2, 3, 1, 1, 4})); // 2
        System.out.println(jump(new int[]{2, 3, 0, 1, 4})); // 2
    }

    // the idea - use kind of BFS/sliding window approach:
    // from the first index, calculate minimum and maximum index you can jump to -
    // that's your first left-right window. then go through each value in this window and
    // calculate what is the furthest jump you can make from this window (lol).
    // furthest jump will be a new window's right boundary, and left will be old-right + 1.
    // do it until right boundary is less than array.
    // each calculated window represents number of steps you need in order to reach
    // its values from the array's beginning
    // 2, 3, 1, 1, 4 -> [2](0 steps), [3, 1] (1 step), [1, 4] (2 steps)
    public static int jump(int[] nums) {
        int left = 0, right = 0, minJumps = 0;

        // no point to continue when right == end
        while (right < nums.length - 1) {
            int furthestJump = 0;
            for (int i = left; i <= right; i++) {
                furthestJump = Math.max(furthestJump, i + nums[i]);
            }

            left = right + 1;
            right = furthestJump;

            minJumps++;
        }

        return minJumps;
    }
}
