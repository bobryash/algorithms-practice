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
}
