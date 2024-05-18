package com.leetcode.easy.arraysandhashing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * #448. Find All Numbers Disappeared in an Array
 *
 * Given an array nums of n integers where nums[i] is in the range [1, n],
 * return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 *
 * Example 1:
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 *
 * Example 2:
 * Input: nums = [1,1]
 * Output: [2]
 */
public class FindAllNumbersDisappearedInAnArray {

    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1})); // [5, 6]
        System.out.println(findDisappearedNumbers(new int[]{1, 1})); // [2]
    }

    // extra space solution
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= nums.length; i++) {
            set.add(i);
        }

        for (int i: nums) {
            set.remove(i);
        }

        return new ArrayList<>(set);
    }

    // constant space solution
    // the idea - values in array are guaranteed to be in 1..n and nums.length == n.
    // so, we can consider each v value in array as an (v - 1) index in array.
    // e.g. array [4, 4, 4, 1], v = nums[0] = 4,
    // convert it into index: i = v - 1 (0-based arrays) = 4 - 1 = 3
    // existence of index 3 tells us, that number 4 exists in array. (i = 3 -> v = i + 1 = 3 + 1 = 4)
    // we need to mark that existence somehow: e.g. change the sign of the value to negative.
    // but we might have duplicates, hence we can refer to that index several times and each time overwrite negative
    // so each time not just take value, but take abs value, so no matter current sign, it'll be turn negative each time
    // after marking, go through each value and check if it's non-negative - meaning i + 1 value is missing
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // map nums[i] to index
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        return result;
    }
}
