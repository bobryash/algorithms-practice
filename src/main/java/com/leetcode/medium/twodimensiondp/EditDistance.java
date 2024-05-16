package com.leetcode.medium.twodimensiondp;

/**
 * #72. Edit Distance
 *
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class EditDistance {

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros")); // 3
        System.out.println(minDistance("intention", "execution")); // 5
    }

    // the idea - 2D dp
    // very similar to 1143. Longest Common Subsequence
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // make room for base cases:
        // - when s1 and s2 are empty = 0 (no operations needed)
        // - when one is empty and other is not = length of non-empty string (we can replace or delete each letter)
        int[][] dp = new int[m + 1][n + 1];

        // set base cases for s1 (right-most column)
        for (int i = m; i >= 0; i--) {
            dp[i][n] = m - i;
        }

        // set base cases for s2 (bottom row)
        for (int j = n; j >= 0; j--) {
            dp[m][j] = n - j;
        }

        // bottom up, from bottom-left square, adjsted to base cases
        // (-1 to skip base cases)
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // chars are equal, no operations needed, skipping both letters move on (diagonally)
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else { // chars are not equal, 1 operations needed - try insert/delete/replace - go into these squares
                    dp[i][j] = 1 + Math.min(dp[i + 1][j + 1],
                            Math.min(dp[i][j + 1], dp[i + 1][j]));
                }
            }
        }

        return dp[0][0];
    }
}
