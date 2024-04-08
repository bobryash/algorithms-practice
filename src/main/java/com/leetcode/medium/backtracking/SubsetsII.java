package com.leetcode.medium.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #90. Subsets II
 *
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 * Example 1:
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class SubsetsII {

    public static void main(String[] args) {
        // System.out.println(subsetsWithDup(new int[]{1, 2, 2})); // [[],[1],[1,2],[1,2,2],[2],[2,2]]
        System.out.println(subsetsWithDup(new int[]{1, 2, 2, 3})); // [[],[1],[1,2],[1,2,2],[2],[2,2]]
        // System.out.println(subsetsWithDup(new int[]{0}));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        helper2(ans, list, nums, 0);
        return ans;
    }

    public static void helper(
            List<List<Integer>> ans,
            List<Integer> tempList,
            int[] nums,
            int start) {
        ans.add(new ArrayList<>(tempList));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            tempList.add(nums[i]);
            helper(ans, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // same with comments
    public static void helper2(
            List<List<Integer>> ans,
            List<Integer> tempList,
            int[] nums,
            int start) {
        // to add empty set - won't happen in for-cycle
        ans.add(new ArrayList<>(tempList));
        System.out.println("Got answer: " + tempList);

        // cycle termination here is an implicit base case.
        // we use cycle's i instead of start for index (as in I).
        // and i's increment shifts index to the next value
        for (int i = start; i < nums.length; i++) {
            // skip the duplicate elements
            if (i > start && nums[i] == nums[i - 1]) {
                System.out.println("Skipped duplicate: " + nums[i]);
                continue;
            }

            System.out.print("Before: " + tempList);
            // same as in Subsets I...
            tempList.add(nums[i]);
            System.out.println(" After: " + tempList  + " index: " + i);
            helper2(ans, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
            // ... but instead of following Subsets I like:
            // helper(answer, tempList, nums, i + 1);
            // here i increment moving index forward + handles dups
            System.out.println("Popped last one");
        }
    }
}
