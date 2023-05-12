package com.leetcode.easy;

/**
 * #28. Find the Index of the First Occurrence in a String
 * <p>
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * Example 2:
 * <p>
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 */
public class FindTheIndexOfTheFirstOccurrenceInString {

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll")); // 2
    }

    /**
     * The solution uses a nested loop to iterate through the haystack string and compare it with the needle string.
     * The outer loop iterates through the haystack string from the first character to the last character that is
     * at least m characters away from the end of the string, where m is the length of the needle string.
     * The inner loop compares each character of the haystack string with the corresponding character of the needle
     * string, and if all the characters match, the index of the first occurrence of the needle string in the
     * haystack string is returned.
     * If no occurrence is found, the function returns -1.
     * <p>
     * Time complexity is O((n-m+1)m)
     */
    // hello ll
    public static int strStr(String haystack, String needle) {
        int hl = haystack.length();
        int nl = needle.length();

        for (int i = 0; i < hl - nl; i++) { // 5 (hello) - 2(ll) = 3 -> "hell" (safe from OutOfBound)
            int j; // new needle counter for every haystack letter..
            for (j = 0; j < nl; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) { // i - static letter from outer haystack loop,
                    break;                                        // j - current letter from inner needle loop
                }                                                 // h -> h != l -> break! | e -> e != l -> break | l -> l == l -> next -> l == l -> stop
            }
            if (j == nl) { // j == nl would mean that in the needle loop we reached the end of the needle string, means that we have a full occurrence
                return i;
            }
        }

        return -1;
    }
}
