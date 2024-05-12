package com.leetcode.medium.twodimensiondp;

/**
 * #1143. Longest Common Subsequence
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace")); // 3
        System.out.println(longestCommonSubsequence("abc", "abc")); // 3
        System.out.println(longestCommonSubsequence("abc", "def")); // 0
    }

    // the idea - 2d dp
    // represent matches between 1st and 2nd strings as 2d map
    // when there's a match - go diagonally (means go to the next sub-problem,
    // which doesn't include matched letters
    // when mismatch - go right and down (means no deal with 2 sets of sub-problems
    // one which includes 1st string with mismatched letter and 2nd without its mismatched
    // and vice versa).
    // in code go bottom up, row by row, result will be in the first square
    public static int longestCommonSubsequence(String text1, String text2) {
        // +1 to accommodate place to zeros of outofbounds (to make math work)
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        // bottom up, from (right-most column)/(bottom row) square
        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                // if matched, then +1 and whatever is in diagonal square (previous match)
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    // if mismatched, then take max from down and right squares
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[0][0];
    }
}
