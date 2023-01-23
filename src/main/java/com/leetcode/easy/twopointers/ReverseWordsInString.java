package com.leetcode.easy.twopointers;

/**
 * #557
 * <p>
 * Given a string s, reverse the order of characters in each word within a sentence while still
 * preserving whitespace and initial word order.
 */
public class ReverseWordsInString {

    public static void main(String[] args) {
        String result = reverseWords("Let's take LeetCode contest");

        System.out.println(result); // s'teL ekat edoCteeL tsetnoc
    }

    public static String reverseWords(String s) {
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = reverseWord(words[i]);
        }
        return String.join(" ", words);
    }

    private static String reverseWord(String word) {
        char[] chars = word.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return new String(chars);
    }
}
