package com.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * #217. Contains Duplicate
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: true
 * <p>
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: false
 * <p>
 * Example 3:
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{1, 2, 3, 1})); // true
        System.out.println(containsDuplicate(new int[]{1, 2, 3, 4})); // false
        System.out.println(containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2})); // true
        System.out.println(containsDuplicate(new int[]{3, 3})); // true

        System.out.println(containsDuplicate2(new int[]{1, 2, 3, 1})); // true
        System.out.println(containsDuplicate2(new int[]{1, 2, 3, 4})); // false
        System.out.println(containsDuplicate2(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2})); // true
        System.out.println(containsDuplicate2(new int[]{3, 3})); // true
    }

    // O(n log(n)) time, O(1) space
    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        return false;
    }

    // O(n) time, O(n) space
    public static boolean containsDuplicate2(int[] nums) {
        Set<Integer> visits = new HashSet<>();

        for (int i : nums) {
            if (visits.contains(i)) {
                return true;
            }
            visits.add(i);
        }

        return false;
    }
}
