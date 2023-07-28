package com.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * #1. Two Sum
 * <p>
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * <p>
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * <p>
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); // [0, 1]
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6))); // [1, 2]
        System.out.println(Arrays.toString(twoSum(new int[]{3, 3}, 6))); // [0, 1]
    }

    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int neededDiff = target - numbers[i];
            Integer secondIndex = valueIndexMap.get(neededDiff);
            if (secondIndex != null) {
                return new int[]{secondIndex, i};
            } else {
                valueIndexMap.put(numbers[i], i);
            }
        }
        return new int[]{-1, -1};
    }
}
