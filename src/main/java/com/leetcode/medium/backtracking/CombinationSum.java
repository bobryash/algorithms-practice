package com.leetcode.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * #39. Combination Sum
 *
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that sum up to
 * target is less than 150 combinations for the given input.
 *
 *
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 *
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 */
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7)); // [[2, 2, 3], [7]]
        System.out.println(combinationSum(new int[]{2, 3, 5}, 8)); // [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
        System.out.println(combinationSum(new int[]{2}, 1)); // []
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();

        backtrack(answer, tempList, candidates, target, 0);
        return answer;
    }

    // dfs
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
                tempList.add(candidates[i]);
                backtrack(answer, tempList, candidates, target - candidates[i], i);
                tempList.remove(tempList.get(tempList.size() - 1));
            }
        }
    }

    // same with comments
    public static void backtrack2(
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
                // when we add a candidate to the current combination here,
                // we're effectively considering that candidate as part of the current combination.
                tempList.add(candidates[i]); // first choice to add
                // by subtracting the candidate value from the target,
                // we're essentially saying that we've used that candidate once in the combination,
                // so we need to reduce the target sum accordingly.
                // we want to explore all possible combinations that include that candidate,
                // so we use the same index (can use candidate as many times as needed - diff from II)
                backtrack2(answer, tempList, candidates, target - candidates[i], i);
                tempList.remove(tempList.get(tempList.size() - 1)); // second choice not to add - undo last add
            }
        }
    }

    // debug
    public static void backtrack3(
            List<List<Integer>> answer,
            List<Integer> tempList,
            int[] candidates,
            int target,
            int start
    ) {
        if (target == 0) {
            answer.add(new ArrayList<>(tempList));
            System.out.println("GOT ANSWER: " + tempList);
        } else if (target < 0) {
            System.out.println("BROKE CONDITION " + "target " + target + " index " + start);
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                System.out.print("Before: " + tempList);
                tempList.add(candidates[i]);
                System.out.println(" After: " + tempList + " index: " + i + " target: " + target);
                backtrack3(answer, tempList, candidates, target - candidates[i],  i);
                tempList.remove(tempList.get(tempList.size() - 1));
                System.out.println("POPPED LAST VALUE");
            }
        }
    }
}
