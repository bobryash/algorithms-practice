package com.leetcode.medium.slidingwindow;

import java.util.Arrays;

/**
 * #567. Permutation in String
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * <p>
 * In other words, return true if one of s1's permutations is the substring of s2.
 * <p>
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <p>
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 */
public class PermutationInString {

    public static void main(String[] args) {
        System.out.println(checkInclusion2("ab", "eidbaooo")); // true
        System.out.println(checkInclusion2("ab", "eidboaoo")); // false
        System.out.println(checkInclusion2("ab", "erab")); // true
    }

    public static boolean checkInclusion(String s1, String s2) {
        // We start off by comparing the lengths of s1 and s2.
        // If s1 is longer than s2, then it is impossible for s1 to be a permutation of s2, and we return false.
        if (s1.length() > s2.length()) {
            return false;
        }

        // If we do not return false, then create two local int arrays, arr1 and arr2.
        // The indices in these arrays represent a letter of the alphabet;
        // index 0 represents 'a', 1 represents 'b', and so on until index 25, which represents 'z'.
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];

        // Then, the for loop determines the count of the characters in s1 and s2 up to s1.length().
        // arr1 will contain the character frequency for s1 and arr2 will contain the character frequency for s2.
        // ***
        // chars a 16-bit integer values (unicode)
        // a = 97; z = 122
        // a - a = 0; z - a = 25;
        for (int i = 0; i < s1.length(); i++) {
            arr1[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }

        // We proceed to compare arr1 and arr2. If they are the same, return true.
        // If not, we move on to the window sliding technique.
        if (Arrays.equals(arr1, arr2)) {
            return true;
        }

        // In the while loop, we examine characters in s2 from front (exclusive) to back (inclusive).
        // In arr2, we decrement the character frequency for the character at index front and increment
        // the character frequency for the character at index back. We then compare arr1 and arr2.
        // If they are the same, return true. If not, increment front and back and move on to the next iteration.
        int front = 0;
        int back = s1.length();
        while (back < s2.length()) {
            arr2[s2.charAt(front) - 'a']--;
            arr2[s2.charAt(back) - 'a']++;

            if (Arrays.equals(arr1, arr2)) {
                return true;
            }
            front++;
            back++;
        }
        // If the while loop terminates then there exists no permutation of s1 in s2. We then return false.
        return false;
    }

    // sliding window recap.
    // the idea - create char frequency array for little string (s1), then go with sliding window
    // (which size is equal to length of little string s1) through s2
    // and compare frequency arrays

    // window here is char frequency in s2

    // about this shrinking part:
    // slidingFreq2[s2.charAt(i - s1.length()) - 'a']--;
    // example:
    // s1.length == 2, i = 2 -> 2-2=0 -> charAt(0)
    // i = 3 -> 3-2=1 -> charAt(1)
    // you shrink frequency from left char to right
    public static boolean checkInclusion2(String s1, String s2) {
        // first create a frequency array for s1, so further frequency comparison with s2
        int[] freq1 = new int[26];
        for (char c : s1.toCharArray()) {
            freq1[c - 'a']++;
        }

        int[] slidingFreq2 = new int[26];
        for (int i = 0; i < s2.length(); i++) {
            slidingFreq2[s2.charAt(i) - 'a']++; // always expand window to the right, start shrink it only when it got bigger than s1
            if (i >= s1.length()) { // to keep a window size equal to s1
                slidingFreq2[s2.charAt(i - s1.length()) - 'a']--; // start shrinking it from the left
            }
            if (Arrays.equals(freq1, slidingFreq2)) {
                return true;
            }
        }

        return false;
    }
}
