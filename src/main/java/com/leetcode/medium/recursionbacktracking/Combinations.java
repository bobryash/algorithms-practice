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
        System.out.println(combine(4, 2)); // [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
    }

    // This solution uses a backtracking algorithm to generate all possible combinations
    // of k elements from the set [1, n].
    // The combine method is the entry point of the solution,
    // it takes as input two integers n and k, and returns a
    // list of all possible combinations of k elements from the set [1, n].
    // The backtrack method is a recursive function that generates all possible combinations by
    // adding elements to a temporary list, recursing on the next element,
    // and then removing the last element.
    // It adds each combination to the result list when the temporary list has k elements.
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        backtrack(answer, tempList, n, k, 1);
        return answer;
    }

    // decision tree - add or not add element
    private static void backtrack(List<List<Integer>> answer, List<Integer> tempList, int n, int k, int start) {
        if (tempList.size() == k) {
            answer.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i <= n; i++) {
            tempList.add(i); // add element
            backtrack(answer, tempList, n, k, i + 1);
            tempList.remove(tempList.size() - 1); // not to add element - undo last add
        }
    }

    // debug
    private static void backtrack2(List<List<Integer>> answer, List<Integer> tempList, int n, int k, int start) {
        if (tempList.size() == k) {
            answer.add(new ArrayList<>(tempList));
            System.out.println("Got answer: " + tempList);
            return;
        }

        for (int i = start; i <= n; i++) {
            System.out.print("Before: " + tempList);
            tempList.add(i);
            System.out.println(" After: " + tempList);
            backtrack2(answer, tempList, n, k, i + 1);
            tempList.remove(tempList.size() - 1);
            System.out.println("Popped last one");
        }
    }
}
