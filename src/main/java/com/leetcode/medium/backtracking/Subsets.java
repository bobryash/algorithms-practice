package com.leetcode.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * #78. Subsets
 * <p>
 * Given an integer array nums of unique elements, return all possible subsets
 * (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class Subsets {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3})); // [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3], []]
        System.out.println(subsets(new int[]{0})); // [[0], []]
    }

    // the idea - to have two choices: when we add or not add an element to result subsets.
    // choice is made -> move to next element. create branches with all possibilities.
    // first recursively add elements, then backtrack by removing last added elements
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        helper(ans, 0, nums, list);
        return ans;
    }

    public static void helper(List<List<Integer>> ans,
                              int start,
                              int[] nums,
                              List<Integer> list) {
        if (start >= nums.length) {
            // reached a leaf (see pic) - have one of a results.
            // create a copy, because list is going to be changed further.
            // it's a shallow copy, but Integer class is immutable,
            // modifying the elements in one list will not affect the other.
            ans.add(new ArrayList<>(list));
        } else {
            // add the element and start the recursive call
            list.add(nums[start]); // first choice - add element
            helper(ans, start + 1, nums, list); // move to the next element
            // remove the element and do the backtracking call.
            list.remove(list.size() - 1); // second choice - do not add element. undo last add by removing last element
            helper(ans, start + 1, nums, list); // move to the next element

        }
    }
}
