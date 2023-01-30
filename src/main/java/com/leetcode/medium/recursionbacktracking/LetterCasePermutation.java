package com.leetcode.medium.recursionbacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * #784
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
        List<String> result = new LetterCasePermutation().letterCasePermutation("a1b2");

        result.forEach(System.out::println);
    }

    // This problem is a good example of one solved using recursion and backtracking.
    // For every letter that can be transformed (upper case to lower case and vice versa),
    // you have to explore both options (with transformation, and without).
    //
    // So, we make the change, and start a branch of recursion down that path.
    // Then, we undo the change, to explore options that are possible only if we had not done that change.
    //
    // Here is a visualization of the recursion tree for the string "a1b2" (one of the sample test cases) (check the pic)
    // The text inside each node is what the current instance of the recursion function sees when it is invoked.
    // The underlined character is the one being considered.
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        recurse(result, s.toCharArray(), 0);
        return result;
    }

    void recurse(List<String> result, char[] chars, int index) {
        //If we have reached a leaf in the recursion tree, save the result.
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        //If char is not a letter, no processing required.
        if (Character.isLetter(chars[index])) {
            //If uppercase char, we make it lower case, and recurse.
            if (Character.isUpperCase(chars[index])) {
                chars[index] = Character.toLowerCase(chars[index]);

                //Start a new branch in the recursion tree, exploring options that are possible only if we had changed the case.
                recurse(result, chars, index + 1);

                //Backtracking. We undo the change so that we can start a new branch in the recursion tree.
                chars[index] = Character.toUpperCase(chars[index]);
            }
            //If lowercase, then we make it upper case, and recurse.
            else {
                chars[index] = Character.toUpperCase(chars[index]);
                recurse(result, chars, index + 1);
                //Backtracking as explained above.
                chars[index] = Character.toLowerCase(chars[index]);
            }
        }
        //This branch explores options that are possible only if the previously performed change (if any) hadn't happened.
        recurse(result, chars, index + 1);
    }
}
