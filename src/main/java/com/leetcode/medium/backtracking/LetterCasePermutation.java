package com.leetcode.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * #784. Letter Case Permutation
 * <p>
 * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
 * <p>
 * Return a list of all possible strings we could create. Return the output in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * Example 2:
 * <p>
 * Input: s = "3z4"
 * Output: ["3z4","3Z4"]
 */
public class LetterCasePermutation {

    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
    }

    // For every letter that can be transformed (upper case to lower case and vice versa),
    // you have to explore both options (with transformation, and without).
    //
    // So, we make the change, and start a branch of recursion down that path.
    // Then, we undo the change, to explore options that are possible only if we had not done that change.
    //
    // Here is a visualization of the recursion tree for the string "a1b2" (one of the sample test cases) (check the pic)
    // The text inside each node is what the current instance of the recursion function sees when it is invoked.
    // The underlined character is the one being considered.
    public static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(result, s.toCharArray(), 0);
        return result;
    }

    private static void backtrack(List<String> result, char[] chars, int index) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        if (Character.isLetter(chars[index])) {
            // Toggle case of the current character
            chars[index] = Character.toUpperCase(chars[index]);
            backtrack(result, chars, index + 1);

            chars[index] = Character.toLowerCase(chars[index]);
            backtrack(result, chars, index + 1);
        } else {
            // If the character is not a letter, proceed to the next character
            backtrack(result, chars, index + 1);
        }
    }
}
