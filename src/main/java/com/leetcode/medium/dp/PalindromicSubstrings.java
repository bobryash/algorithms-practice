package com.leetcode.medium.dp;

/**
 * #647. Palindromic Substrings
 *
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 *
 * Example 1:
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 */
public class PalindromicSubstrings {

    public static void main(String[] args) {
        System.out.println(countSubstrings("abc")); // 3
        System.out.println(countSubstrings("aaa")); // 6
    }

    // the idea - same as in #5 - The Longest Palindromic substring:
    // for each char in a string, try to expand to left and to the right from it,
    // trying to find a palindrome.
    // when chars from left and right of the center char match, expand and do count++
    // N.B.: do not forget about even-centered palindromes! "abba"
    public static int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expandFromCenter(s, i, i); // for odd centered palindromes: "aba"
            count += expandFromCenter(s, i, i + 1); // for even centered palindromes: "abba"
        }

        return count;
    }

    // basically function from #5(The Longest Substring Palindrome), slightly modified
    private static int expandFromCenter(String s, int start, int end) {
        int left = start, right = end;
        int count = 0;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // expand
            left--;
            right++;
            count++;
        }

        return count;
    }
}
