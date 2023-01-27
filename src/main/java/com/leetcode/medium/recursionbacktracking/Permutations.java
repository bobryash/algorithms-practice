package com.leetcode.medium.recursionbacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * #46
 * <p>
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 */
public class Permutations {

    public static void main(String[] args) {
        List<List<Integer>> result = new Permutations().permute(new int[] {1, 2, 3});

        result.forEach(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    // The backtrack method is a recursive function that generates all possible permutations by adding elements to a
    // temporary list, recursing on the next element, and then removing the last element.
    // It adds each permutation to the result list when the temporary list has the same length as the input array.
    //
    //The key difference between this solution and the solution for problem #77 is that this solution doesn't use a
    // start variable and it checks if the element is already in tempList before adding it. This is to prevent the
    // same element from being added multiple times, since permutation involves all the possible arrangement of a set
    // of elements, so the same element can't be used multiple times.
    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int num : nums) {
            if (tempList.contains(num)) {
                continue;
            }

            tempList.add(num);
            backtrack(result, tempList, nums);
            tempList.remove(tempList.size() - 1);
        }
    }
}
