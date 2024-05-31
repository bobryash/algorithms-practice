package com.leetcode.easy.arraysandhashing;

/**
 * #58. Length of Last Word
 *
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 *
 * A word is a maximal
 * substring
 *  consisting of non-space characters only.
 *
 *
 * Example 1:
 * Input: s = "Hello World"
 * Output: 5
 * Explanation: The last word is "World" with length 5.
 *
 * Example 2:
 * Input: s = "   fly me   to   the moon  "
 * Output: 4
 * Explanation: The last word is "moon" with length 4.
 *
 * Example 3:
 * Input: s = "luffy is still joyboy"
 * Output: 6
 * Explanation: The last word is "joyboy" with length 6.
 */
public class LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World")); // 5
        System.out.println(lengthOfLastWord("   fly me   to   the moon  ")); // 4
        System.out.println(lengthOfLastWord("luffy is still joyboy")); // 6
    }

    public static int lengthOfLastWord(String s) {
        // remove leading and trailing zeroes
        s = s.trim();
        int lastWordLength = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            }

            lastWordLength++;
        }

        return lastWordLength;
    }

    public static int lengthOfLastWord2(String s) {
        // \s is for space, \s+ - any number of spaces
        String[] words = s.split("\\s+");
        return words[words.length - 1].length();
    }
}
