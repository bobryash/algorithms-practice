package com.leetcode.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * #131. Palindrome Partitioning
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome
 * Return all possible palindrome partitioning of s.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        System.out.println(partition("abb")); // [["a","a","b"],["aa","b"]]
        System.out.println(partition("a")); // [["a"]]
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> answer = new ArrayList<>();
        List<String> currentList = new ArrayList<>();
        backtrack(answer, currentList, s, 0);
        return answer;
    }

    // the goal: to partition string in palindromes sets, in all combinations: [["a","a","b"],["aa","b"]]
    private static void backtrack(List<List<String>> answer, List<String> currentList, String s, int start) {
        if (start >= s.length()) { // base case - no more letters to add
            answer.add(new ArrayList<>(currentList));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) { // only for palindrome paths
                currentList.add(s.substring(start, end + 1)); // add
                backtrack(answer, currentList, s, end + 1); // END + 1, not start
                currentList.remove(currentList.size() - 1); // not to add
            }
        }
    }

    // classique
    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
