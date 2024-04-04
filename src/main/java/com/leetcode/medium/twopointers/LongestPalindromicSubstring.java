package com.leetcode.medium.twopointers;

/**
 * #5. Longest Palindromic Substring
 *
 * Given a string s, return the longest
 * palindromic substring in s.
 *
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
    }

    // the idea - for each char in string, try to expand left and right, and check if it's a palindrome
    // (common technique), then compare it to previous max palindrome length and update it.
    // if we now length of palindrome, we can calculate start and end indexes relative to center by dividing length/2.
    // trickiest part is odd/even palindrome indexes consideration
    // (hard to memorize all these -1 +1, just try to calculate it for "aba" to check)
    public static String longestPalindrome(String s) {
        int palStart = 0, palEnd = 0, currPalLength = 0;
        for (int i = 0; i < s.length(); i++) {
            int oddLength = expandAroundCenter(s, i, i); // For odd length palindromes
            int evenLength = expandAroundCenter(s, i, i + 1); // For even length palindromes
            currPalLength = Math.max(oddLength, evenLength);

            if (currPalLength > palEnd - palStart) {
                // -1 to do not include center char of palindrome
                // "i - 1 - len/2" works only for odd palindrome, the one below works for both odd and even, e.g:
                // odd palindrome: abba -> ab(center)ba
                // length = 4 - (-1) - 1 = 4 (check line 55), i = 1
                // NOT OK: start = i - 1 - len / 2 = 1 - 1 - 4 / 2 = 0 - 2 = -2 (but works for odd palindrome)
                // OK:     start = i - (len - 1) / 2 =  1 - (4 - 1) / 2 = 0
                palStart = i - (currPalLength - 1) / 2;
                palEnd = i + currPalLength / 2;
            }
        }

        return s.substring(palStart, palEnd + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // Length of the palindrome
    }
}
