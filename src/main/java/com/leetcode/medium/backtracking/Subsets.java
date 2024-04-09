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
 * (if [1,2], than [2,1] is a duplicate set)
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

    // dfs
    // the idea - to have two choices: when we add or not add an element to result subsets.
    // choice is made -> move to next element. create branches with all possibilities.
    // first recursively add elements, then backtrack by removing last added elements
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        backtrack(ans, tempList, nums, 0);
        return ans;
    }

    private static void backtrack(List<List<Integer>> answer,
                                  List<Integer> tempList,
                                  int[] nums,
                                  int start) {
        answer.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(answer, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // same with comments
    private static void backtrack2(List<List<Integer>> answer,
                                   List<Integer> tempList,
                                   int[] nums,
                                   int start) {
        answer.add(new ArrayList<>(tempList));
        System.out.println("Got answer: " + tempList);
        for (int i = start; i < nums.length; i++) {
            System.out.print("Before: " + tempList);
            // first choice - add element
            tempList.add(nums[i]);
            System.out.println(" After: " + tempList + " Start: " + start);
            backtrack2(answer, tempList, nums, i + 1);
            // second choice - do not add element. undo last add by removing last element
            tempList.remove(tempList.size() - 1);
            System.out.println("Popped last one");
        }
    }

    // old obsolete NeetCode's version, inconsistent with his own Subsets II (for Java)
    private static void backtrack3(List<List<Integer>> answer,
                                   List<Integer> tempList,
                                   int[] nums,
                                   int start) {
        if (start >= nums.length) {
            // reached a leaf (see pic) - have one of a results.
            // create a copy, because list is going to be changed further.
            // it's a shallow copy, but Integer class is immutable,
            // modifying the elements in one list will not affect the other.
            answer.add(new ArrayList<>(tempList));
            System.out.println("Got to leaf: " + tempList);
        } else {
            System.out.print("Before: " + tempList);
            // add the element and start the recursive call
            tempList.add(nums[start]); // first choice - add element
            System.out.println(" After: " + tempList + " Start: " + start);
            backtrack3(answer, tempList, nums, start + 1 ); // move to the next element
            // remove the element and do the backtracking call.
            tempList.remove(tempList.size() - 1); // second choice - do not add element. undo last add by removing last element
            System.out.println("Popped last one");
            backtrack3(answer, tempList, nums, start + 1); // move to the next element
        }
    }
}
