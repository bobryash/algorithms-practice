package com.leetcode.medium.twopointers;

/**
 * #424. Longest Repeating Character Replacement
 * <p>
 * You are given a string s and an integer k. You can choose any character of the string and change it to any
 * other uppercase English character. You can perform this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter you
 * can get after performing the above operations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * <p>
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exist other ways to achieve this answer too.
 */
public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2)); // 4
        System.out.println(characterReplacement("AABABBA", 1)); // 4
    }

    // the idea - sliding window
    public static int characterReplacement(String s, int k) {
        int left = 0, right = 0; // borders of a sliding window
        int maxFreq = 0; // current maximum of repeating character
        int answer = 0; // longest substring with same letter after allowed replacements
        int[] freqs = new int[26]; // map with characters frequency counts

        while (right < s.length()) { // could be replaced with for-loop, but "while" is easier for me to remember
            char curChar = s.charAt(right);
            freqs[curChar - 'A']++;
            maxFreq = Math.max(maxFreq, freqs[curChar - 'A']);

            // If the number of replacements needed is greater than k (number of replacements allowed),
            // move the left pointer to shrink the window
            if (right - left + 1 - maxFreq > k) { // "A[BBABA]BA", l=1,r=5,k=1 -> BBABA 5-1+1-3(BBB) = 2, 2 > 1
                freqs[s.charAt(left) - 'A']--;
                left++;
            } else {
                // Update the maximum length of the valid substring
                answer = Math.max(answer, right - left + 1);
            }
            right++; // always forward, because next char could be potentially replaced. check the comment below for details
        }

        return answer;
    }
}

/*
 * The reason for the difference in the behavior of the right border between the two solutions (#3 and this one) lies in
 * the specific problems they are solving.
 * <p>
 * Longest Repeating Character Replacement : In this problem, the goal is to find the
 * longest substring with repeating characters while allowing at most k replacements. Therefore, in this solution, the
 * right border (right) is allowed to keep moving forward because even if a new character is encountered, it can
 * potentially be replaced (as long as the number of replacements made so far is less than or equal to k).
 * This allows for the possibility of extending the length of the substring and finding a longer one.
 * <p>
 * Longest Substring Without Repeating Characters : In this problem, the goal is to find the
 * longest substring without repeating characters. Therefore, the right border (right) in this solution only moves when
 * it's certain that the current character is not a repeat. This ensures that the substring under consideration doesn't
 * have any repeating characters. If a repeating character is encountered, the left border (left) is moved to exclude the
 * repeating character from the substring. This maintains the condition of no repeating characters in the substring.
 * <p>
 * So, the difference in the behavior of the right border is because the two problems have different requirements.
 * In the first problem, allowing the right border to move freely can potentially lead to a longer valid substring with
 * repeated characters. In the second problem, you want to avoid any repeating characters, so the right border only moves
 * when it's safe to do so without introducing repeats.
 */
