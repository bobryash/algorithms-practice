package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * #169. Majority Element
 * Easy
 * 15.1K
 * 452
 * Companies
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * <p>
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement {

    public static void main(String[] args) {
        System.out.println(majorityElementExtraSpace(new int[]{3, 2, 3}));
        System.out.println(majorityElementExtraSpace(new int[]{2, 2, 1, 1, 1, 2, 2}));
        System.out.println(majorityElementBoyerMoore(new int[]{3, 3, 4}));
        System.out.println(majorityElementBoyerMoore(new int[]{2, 2, 1, 3, 4, 2, 2}));
    }

    public static int majorityElementExtraSpace(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        int result = 0; // number from array
        int maxCount = 0; // max numbers appearances count

        for (int i: nums) {
            count.put(i, count.getOrDefault(i, 0) + 1);
            if (count.get(i) > maxCount) {
                maxCount = count.get(i);
                result = i;
            }
        }

        return result;
    }

    // constant space
    public static int majorityElementBoyerMoore(int[] nums) {
        int count = 0;
        int result = 0;

        for (int i : nums) {
            if (count == 0) {
                result = i;
            }
            count = i == result ? count + 1 : count - 1;
        }

        return result;
    }
}
