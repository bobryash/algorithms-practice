package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * #22. Generate Parentheses
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(String.join(", ", generateParenthesis(3))); // ((())), (()()), (())(), ()(()), ()()()
        // System.out.println(String.join(", ", generateParenthesis(1))); // ()
    }

    static List<String> result = new ArrayList<>();

    public static List<String> generateParenthesis(int n) {
        backtrack(0, 0, n, "");
        return result;
    }

    // backtracking power
    static void backtrack(int opened, int closed, int n, String s) {
        if (opened == closed && opened == n) { // base case
            result.add(s);
        }

        if (opened < n) { // can only add ( when some capacity is left
            backtrack(opened + 1, closed, n, s + "(");
        }

        if (closed < opened) { // make sense to add closed only when there are some opened
            backtrack(opened, closed + 1, n, s + ")");
        }
    }
}
