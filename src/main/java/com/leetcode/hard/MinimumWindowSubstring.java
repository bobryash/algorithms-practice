package com.leetcode.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * #76. Minimum Window Substring
 * <p>
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring
 * of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * <p>
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * <p>
 * Example 2:
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // "BANC"
        System.out.println(minWindow("a", "a")); // "a"
        System.out.println(minWindow("a", "aa")); // ""
        System.out.println(minWindow("aa", "aa")); // "aa"
    }

    // the idea - sliding window.
    // in window, we compare matches. match++ is when both target map (char frequencies in target string) and window map have same frequencies (A-2, A-2, match++).
    // if we got all the required matches in window, we have a potential answer, but we will try to make window even shorter, and will start to shrink it from the left.
    // with each shrink we check if it affects our matches count and update it if it does.
    // important thing to remember - for result we need to find minLeft(from where to substring) and minLength(how much to substring).
    public static String minWindow(String s, String t) {
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int minLeft = 0; // from where to substring
        int minLength = Integer.MAX_VALUE; // how much to substring
        int matchCount = 0; // count++ is when character and its frequency are the same in both maps (targM A-2, w winM A-2)
        int neededCount = targetMap.size();

        Map<Character, Integer> windowMap = new HashMap<>();
        int left = 0;

        // sliding window
        for (int right = 0; right < s.length(); right++) { // expanding it to the right
            char rightChar = s.charAt(right);
            windowMap.put(rightChar, windowMap.getOrDefault(rightChar, 0) + 1);

            if (targetMap.containsKey(rightChar) && Objects.equals(targetMap.get(rightChar), windowMap.get(rightChar))) {
                matchCount++;
            }

            // if true, then we have a potential minLength of substring, but with "while" we will try to make it even smaller
            while (neededCount == matchCount && left <= right) {
                // we have all matches, now save our potential result
                int currentLength = right - left + 1; // we have all needed matches, and this is our potential substring length
                if (currentLength < minLength) {
                    minLength = currentLength;
                    minLeft = left;
                }

                // let's try to make our window even smaller
                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                left++;

                // and don't forget to check if it affects the matches
                if (targetMap.containsKey(leftChar) && targetMap.get(leftChar) > windowMap.get(leftChar)) {
                    matchCount--;
                }
            }
        }

        if (minLength == Integer.MAX_VALUE) return "";

        return s.substring(minLeft, minLeft + minLength);
    }
}
