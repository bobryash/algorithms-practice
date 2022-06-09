package com.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWORepeatingChars {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("heeey"));
    }

    public static String lengthOfLongestSubstring(String input) {
        Map<Character, Integer> visited = new HashMap<>();
        String output = "";
        for (int start = 0, end = 0; end < input.length(); end++) {
            char currChar = input.charAt(end);
            if (visited.containsKey(currChar)) {
                start = Math.max(visited.get(currChar)+1, start);
            }
            if (output.length() < end - start + 1) {
                output = input.substring(start, end + 1);
            }
            visited.put(currChar, end);
        }
        return output;
    }

    // Time Complexity: O(n); Space Complexity: O(n)
    public int lengthOfLongestSubstring1(String s) {

        /**
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
}


