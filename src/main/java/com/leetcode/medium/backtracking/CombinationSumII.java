package com.leetcode.medium.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #40. Combination Sum II
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 *
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class CombinationSumII {
    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8)); // [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
        System.out.println(combinationSum2(new int[]{2, 5, 2, 1, 2}, 5)); // [[1, 2, 2], [5]]
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        List<Integer> ls = new ArrayList<Integer>();
        backtrack(answer, ls, candidates, target, 0);
        return answer;
    }

    // difference from CombinationSum #39:
    // 1. candidates contain duplicates (which we need to skip)
    // 2. each candidate can't be used unlimited time, only once
    public static void backtrack(
            List<List<Integer>> answer,
            List<Integer> tempList,
            int[] candidates,
            int target,
            int start
    ) {
        if (target == 0) {
            answer.add(new ArrayList<>(tempList));
        } else if (target < 0) {
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                // skip duplicates (the only diff from problem I)
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                // same as in I
                tempList.add(candidates[i]); // first choice to add
                backtrack(answer, tempList, candidates, target - candidates[i], i + 1);
                tempList.remove(tempList.get(tempList.size() - 1)); // second choice not to add - undo last add
            }
        }
    }

    // debug
    public static void backtrack2(
            List<List<Integer>> answer,
            List<Integer> tempList,
            int[] candidates,
            int target,
            int start
    ) {
        if (target == 0) {
            answer.add(new ArrayList<>(tempList));
            System.out.println("Got answer: " + tempList);
        } else if (target < 0) {
            System.out.println("Broke condition, target: " + target);
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                System.out.print("Before: " + tempList);
                tempList.add(candidates[i]);
                System.out.println(" After: " + tempList + " index: " + i);
                backtrack2(answer, tempList, candidates, target - candidates[i], i + 1);
                tempList.remove(tempList.get(tempList.size() - 1));
                System.out.println("Popped last one");
            }
        }
    }
}
