package com.leetcode.medium.greedy;

/**
 * #55. Jump Game
 *
 * You are given an integer array nums.
 * You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what.
 * Its maximum jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4})); // true
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4})); // false
    }

    // O(n)
    // the idea - by description you start from index 0, and the goal is the end of array.
    // but imagine you start at index end - 1, then ask if you can get from it to the end
    // (i + nums[i] >= goal), if true, then you can forget about current end,
    // and make current i a new goal, then move until start.
    // if you can get to the start (index 0) this way, then you can jump from 0 to the end.
    public static boolean canJump(int[] nums) {
        // need to reach the last INDEX of array
        int goal = nums.length - 1;
        // start with end - 1, and ask if we can jump from i to the goal (end)
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) { // jump from index i by length nums[i]
                goal = i;
            }
        }

        return goal == 0; // if we reach start, then it's possible to get from start to end
    }
}
