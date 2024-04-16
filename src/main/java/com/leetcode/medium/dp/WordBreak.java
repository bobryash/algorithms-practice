package com.leetcode.medium.dp;

import java.util.List;

/**
 * #139. Word Break
 *
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 */
public class WordBreak {

    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", List.of("leet", "code"))); // true
        System.out.println(wordBreak("applepenapple", List.of("apple", "pen"))); // true
        System.out.println(wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat"))); // false
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        // +1 to make room for base case value - empty sring in the end of s
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true; // "leetcode"_

        // build answer from right to left (from base case)
        for (int i = s.length() - 1; i >= 0; i--) {
            for (String word: wordDict) {
                // if word fits in s, and if it mathches inside
                // code in leet[code]
                if (i + word.length() <= s.length() && s.startsWith(word, i)) {
                    // the core of dp - we check previously calculated answer
                    // for index which is i + word.length away from current one
                    // and if there we also had a match, then here we confirm another match
                    dp[i] = dp[i + word.length()];
                }
                // or else other words might override previously set "true" value by "false"!
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[0]; // answer has been built in 0
    }
}
