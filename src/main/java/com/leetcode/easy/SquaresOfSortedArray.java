package com.leetcode.easy;

import java.util.Arrays;

/**
 * 977
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted
 * in non-decreasing order.
 *
 * Example 1:
 *
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 */
public class SquaresOfSortedArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares(new int[]{-4, -1, 0, 3, 10}))); // [0, 1, 9, 16, 100]
    }

    /**
     * The crux over here is that the array is already sorted.
     * We are comparing the first and last elements because after square these have the possibility of being
     * the highest element (nb: array may contain negative values! -> thus abs usage)
     * Both the extremes contain the max element (after square ofc), so we are inserting these elements to
     * the last of the new array to make it sorted.
     */
    public static int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        int left = 0, right = length - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[i] = nums[left] * nums[left];
                left++;
            } else {
                result[i] = nums[right] * nums[right];
                right--;
            }
        }
        return result;
    }
}
