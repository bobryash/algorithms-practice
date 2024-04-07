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

        backtrack(candidates, target, answer, tempList, 0);
        return answer;
    }

    // dfs
    public static void backtrack(
            int[] candidates,
            int target,
            List<List<Integer>> ans,
            List<Integer> cur,
            int index
    ) {
        if (target == 0) {
            ans.add(new ArrayList<>(cur));
        } else if (target < 0 || index >= candidates.length) {
            return;
        } else {
            cur.add(candidates[index]);
            backtrack(candidates, target - candidates[index], ans, cur, index);

            cur.remove(cur.get(cur.size() - 1));
            backtrack(candidates, target, ans, cur, index + 1);
        }
    }

    // same but with tons of comments and debug output
    public static void backtrack2(
            int[] candidates,
            int target,
            List<List<Integer>> ans,
            List<Integer> cur,
            int index
    ) {
        if (target == 0) {
            ans.add(new ArrayList<>(cur));
            System.out.println("GOT ANSWER: " + cur);
        } else if (target < 0 || index >= candidates.length) {
            System.out.println("BROKE CONDITION " + "target " + target + " index " + index);
            return;
        } else {
            System.out.print("Before: " + cur + " ");
            // when we add a candidate to the current combination here,
            // we're effectively considering that candidate as part of the current combination.
            cur.add(candidates[index]);
            System.out.println("After: " + cur + " index: " + index + " target: " + target);
            // by subtracting the candidate value from the target,
            // we're essentially saying that we've used that candidate once in the combination,
            // so we need to reduce the target sum accordingly.
            // we want to explore all possible combinations that include that candidate,
            // so we use the same index.
            backtrack(candidates, target - candidates[index], ans, cur, index);

            cur.remove(cur.get(cur.size() - 1));
            System.out.println("POPPED LAST VALUE");
            // start adding next candidates in the combination - shift index by 1
            backtrack(candidates, target, ans, cur, index + 1);
        }
    }
}
