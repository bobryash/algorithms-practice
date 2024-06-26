package com.leetcode.medium.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * #3
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWORepeatingChars {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring3("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring3("bbbbb")); // 1
        System.out.println(lengthOfLongestSubstring3("pwwkew")); // 3
    }

    /**
     * The outer while loop iterates through the entire string, with the i and j pointers moving along the string one
     * character at a time. The inner if statement checks if the current character at index j is already in the set.
     * If it is not, the character is added to the set and the j pointer is incremented.
     * If the character is already in the set, the first character at index i is removed from the set and the i pointer
     * is incremented. The value of max is updated with the maximum length of the current substring.
     */
    public static int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0, max = 0;
        Set<Character> set = new HashSet<>();

        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return max;
    }

    public static String lengthOfLongestSubstring1(String input) {
        Map<Character, Integer> visited = new HashMap<>();
        String output = "";
        for (int start = 0, end = 0; end < input.length(); end++) {
            char currChar = input.charAt(end);
            if (visited.containsKey(currChar)) {
                start = Math.max(visited.get(currChar) + 1, start);
            }
            if (output.length() < end - start + 1) {
                output = input.substring(start, end + 1);
            }
            visited.put(currChar, end);
        }
        return output;
    }

    // Time Complexity: O(n); Space Complexity: O(n)
    public int lengthOfLongestSubstring2(String s) {

        /*
         * Recording all visited chars
         * @param Character - the char visited
         * @param Integer - last index of the visited char
         */
        HashMap<Character, Integer> charRecorderMap = new HashMap<>();

        int maxNonDuplicateSubstringLength = 0;
        int newSubstringStartPos = 0;
        int lenString = s.length();

        for (int currentPos = 0; currentPos < lenString; currentPos++) {

            // luckily, String.charAt(N) takes O(1) in Time Complexity
            char theChar = s.charAt(currentPos);

            if (charRecorderMap.containsKey(theChar)) {
                // newSubstringStartPos is indeed the last occurrence of the same char + 1
                // say, for string "abbac",
                // when
                // - `currentPos` == 0, theChar == "a", charRecorderMap.containsKey(theChar) == false, `newSubstringStartPos` == 0;
                // - `currentPos` == 1, theChar == "b", charRecorderMap.containsKey(theChar) == false, `newSubstringStartPos` == 0;
                // - `currentPos` == 2, theChar == "b", charRecorderMap.containsKey(theChar) == true,  `newSubstringStartPos` == 2;
                //    charRecorderMap.get(theChar) == 1;
                // that means, if we found any char (say, "b") is duplicated,
                // we should reset the substring start position to the position right after the duplicated char (+1)

                // - `currentPos` == 3, theChar == "a", charRecorderMap.containsKey(theChar) == true,  `newSubstringStartPos` == 2;
                //    charRecorderMap.get(theChar) == 0;
                //    charRecorderMap.get(theChar) + 1 < newSubstringStartPos // charRecorderMap.get(theChar)  is actually the first "a", which is at pos 0;

                // That is to say, we should always set `newSubstringStartPos` to the rightmost position of any duplicated chars
                newSubstringStartPos = Math.max(newSubstringStartPos, charRecorderMap.get(theChar) + 1);
            }

            // Why `currentPos - newSubstringStartPos + 1`?
            // Well, `currentPos - newSubstringStartPos` is the diff of start pos and end pos of the current substring
            // `+ 1` is because we should add 1 to the diff. Say for substring "ab", "a" is at pos 0, "b" is at pos 1,
            // the diff is 1, but the count of the total string length is 2.
            maxNonDuplicateSubstringLength = Math.max(maxNonDuplicateSubstringLength, currentPos - newSubstringStartPos + 1);

            charRecorderMap.put(theChar, currentPos);
        }

        return maxNonDuplicateSubstringLength;
    }

    // sliding window approach recap.
    // if char is not in set, then check for maxLength and expand a window to the right.
    // if char IS in set, then shrink the window from the left by
    // moving left pointer and removing current left value from the set
    public static int lengthOfLongestSubstring3(String s) {
        int left = 0, right = 0, maxLength = 0;
        Set<Character> set = new HashSet<>();

        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1); // +1 is to deal with edge case like 0 - 0 + 1 (zero based!)
                right++; // expand window when there are no dups
            } else {
                set.remove(s.charAt(left));
                left++; // shrink window when there are dups
            }
        }

        return maxLength;
    }
}
