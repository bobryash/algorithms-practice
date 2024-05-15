package com.leetcode.medium.twodimensiondp;

/**
 * #97. Interleaving String
 * <p>
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * <p>
 * An interleaving of two strings s and t is a configuration where s and t are divided into n and m
 * substrings
 * respectively, such that:
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 * <p>
 * <p>
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Explanation: One way to obtain s3 is:
 * Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
 * Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
 * Since s3 can be obtained by interleaving s1 and s2, we return true.
 * <p>
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
 * <p>
 * Example 3:
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 */
public class InterleavingString {

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac")); // true
        //System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc")); // false
        //System.out.println(isInterleave("", "", "")); // true
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        // check if lengths allow to construct s3 at all
        if (s1.length() + s2.length() != s3.length()) return false;

        // 2d dp array. each square represents whether it is possible to form the substring
        //s3[i+j:] using the suffixes s1[i:] and s2[j:]
        // + 1 to make room to the cases, when one string doesn't have any more letters (check pics)
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        // base case - end of each string - we can construct s3
        dp[s1.length()][s2.length()] = true;

        // for each square, starting from the adjusted to base case, bottom up, row by row
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                // i + 1, because letter is a match, move to the next one in that string
                // i + j will always get us to the current char in s3 (check pics)
                if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j) && dp[i + 1][j]) {
                    dp[i][j] = true;
                }
                // same here
                if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j) && dp[i][j + 1]) {
                    dp[i][j] = true;
                }
            }
        }

        // answer is in the first square
        return dp[0][0];
    }
}
