package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 Return indices of two numbers, which sum equals target value
 Example:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Output: Because nums[0] + nums[1] == 9, we return [0, 1]
 **/
public class SumOfTwo {

    // time: O(n*n)
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int currentValue = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (target == sum) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // time: O(n), memory: O(n)
    public int[] twoSumHashMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // <value, index>
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i]; // 7 = 9 - 2
            Integer secondIndex = map.get(value);
            if (secondIndex != null) {
                return new int[]{secondIndex, i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}
