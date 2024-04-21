package com.leetcode.medium.dp;

/**
 * #91. Decode Ways
 *
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into
 * letters using the reverse of the mapping above (there may be multiple ways).
 * For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 * Example 1:
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 */
public class DecodeWays {

    // This problem is a Fibonacci type of DP
    // (fibonacci sequence with condition?).
    // Super important to remember for such problems: cur value is the sum of previously calculated sums of values:
    // dp[i] = dp[i - 1] + dp[i - 2]
    // Current value by itself is not considered at the start, it's only a sum of previous calculations.
    // Sums are calculated from base cases (prev = 1, prevPrev = 1).
    // Sometimes you need to get over the array, to calculate the final value, like here
    // or in #746 MinCostClimbingStairs (last ladder is beyond the array too).
    public static void main(String[] args) {
        System.out.println(numDecodings("12")); // 2
        System.out.println(numDecodings("226")); // 3
        System.out.println(numDecodings("11")); // 2
        System.out.println(numDecodings("06")); // 0
    }

    // for each index, current value will be equal to sum of previously calculated sums:
    // num of ways to decode string as 1 digits, and as 2 digits
    // d[i] = d[i - 1] + d[i - 2]
    // we go beyond string length to calculate the last sum.
    // each sum is added only if condition is true: can decode as 1 digit (+prev), as 2 digits (+prevPrev)
    // each iteration we shift pervPrev and prev pointers to the right.
    //
    // Example for input 226:
    // 0. base case calculation outside the loop:
    // for i == 1: 22(i)6: prev = 1(only one way to parse "2" at index 0, prevPrev = 1 (empty string: _226)
    // 1. start with i == 2: 226(i)
    // 2(i - 1) - ok, +prev; 22 - ok, +prevPrev; cur = prev+prevPrev = 2; prevPrev = prev = 1; prev = cur = 2
    // 2. go beyond s length to calculate final sum, i == 3:
    // 6(i - 1) - ok, + prev; 26 - ok, + prevPrev; cur = prev+prevPrev = 3; prevPrev = prev = 2; prev = cur = 3(result)
    //
    public static int numDecodings(String s) {
        int prevPrev = 1; // empty string
        int prev = s.charAt(0) == '0' ? 0 : 1; // only one way to decode single char (and if 0 - return 0)

        for (int i = 2; i <= s.length(); i++) {
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            int cur = 0;

            if (1 <= oneDigit && oneDigit <= 9) {
                cur += prev;
            }
            if (10 <= twoDigits && twoDigits <= 26) {
                cur += prevPrev;
            }

            prevPrev = prev;
            prev = cur;
        }

        return prev;
    }

    // if use dp array, index i in for-each loop is not related to any input (string' or array' index),
    // it's a current dynamic calculation which is basing on previous calculations, starting with base cases
    // _226
    public static int numDecodings2(String s) {
        // +1 to make room for one of base cases - first empty string
        int[] dp = new int[s.length() + 1];
        dp[0] = 1; // empty string
        dp[1] = s.charAt(0) == '0' ? 0 : 1; // strings which start with 0 are invalid

        for (int i = 2; i < dp.length; i++) {
            // these two ints both represent letter indexes in alphabet
            // (substring's last index is non-inclusive + handles outofbounds exception)
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            // can parse as 1 digit
            if (1 <= oneDigit && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }

            // can parse as 2 digits
            if (10 <= twoDigits && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[dp.length - 1];
    }
}
