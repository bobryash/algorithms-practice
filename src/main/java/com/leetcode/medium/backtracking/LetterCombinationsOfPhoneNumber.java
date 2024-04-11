package com.leetcode.medium.backtracking;

import java.util.*;

/**
 * #17. Letter Combinations of a Phone Number
 *
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Example 2:
 * Input: digits = ""
 * Output: []
 *
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 */
public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
        //System.out.println(letterCombinations(""));
        //System.out.println(letterCombinations("2"));
    }

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return Collections.emptyList();

        Map<Character, String> charToNumbers = Map.of(
                '2', "abc",
                '3', "def",
                '4', "ghi",
                '5', "jkl",
                '6', "mno",
                '7', "pqrs",
                '8', "tuv",
                '9', "wxyz");
        List<String> answer = new ArrayList<>();
        StringBuilder tempSb = new StringBuilder();
        backtrack(digits, charToNumbers, tempSb, answer, 0);
        return answer;
    }

    // FOR EACH digit(e.g. 2) in OG string(23), we're going to extract associated string of letters (e.g. abc)
    // break it to array, and then FOR EACH letter we're going to append to result and backtrack to next DIGIT.
    // backtrack index is for digit in input (23), and for-each cycle is for associated letters.
    // Example:
    // take 2 -> get abc -> start cycle with a -> append "a" to "" -> go next backtrack ->
    // take 3 -> get def -> start cycle with d, append "d" to "a" -> go next backtrack ->
    // hit base case ("23".length() = 2 (index)), add "ad" to result
    // start pop last values, backtrack further
    //
    private static void backtrack(String digits, Map<Character, String> charToNumbers,
                                  StringBuilder sb, List<String> answer, int index) {
        if (index == digits.length()) {
            answer.add(sb.toString());
            return;
        }

        char digit = digits.charAt(index); // get next digit
        String letters = charToNumbers.get(digit); // get next letters set

        for (char letter: letters.toCharArray()) {
            sb.append(letter); // add letter
            backtrack(digits, charToNumbers, sb, answer, index + 1); // proceed with next digit
            sb.deleteCharAt(sb.length() - 1); // not to add letter. undo last add
        }
    }
}
