package com.leetcode.medium.recursionbacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * #77
 * <p>
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * <p>
 * You may return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 */
public class Combinations {

    public static void main(String[] args) {
        List<List<Integer>> result = new Combinations().combine(4, 2);

        result.forEach(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });
    }

    // This solution uses a backtracking algorithm to generate all possible combinations of k elements from the set [1, n].
    // The combine method is the entry point of the solution, it takes as input two integers n and k, and returns a
    // list of all possible combinations of k elements from the set [1, n]. The backtrack method is a recursive function
    // that generates all possible combinations by adding elements to a temporary list, recursing on the next element,
    // and then removing the last element. It adds each combination to the result list when the temporary list has k
    // elements.
    //
    // Note that this solution uses the in-built java List interface, which is a general-purpose implementation of
    // a List. If you are working with large input values or large number of outputs, you may want to consider using a
    // more specific implementation that is more efficient for your use case.
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int start, int n, int k) {
        if (tempList.size() == k) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i <= n; i++) {
            tempList.add(i);
            backtrack(result, tempList, i + 1, n, k);
            tempList.remove(tempList.size() - 1);
        }
    }
}
