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

    static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(String.join(", ", generateParenthesis(3))); // ((())), (()()), (())(), ()(()), ()()()
        // System.out.println(String.join(", ", generateParenthesis(1))); // ()
    }

    public static List<String> generateParenthesis(int n) {
        backtrack(0, 0, n, "");
        return result;
    }

    // backtracking power
    static void backtrack(int openN, int closedN, int n, String s) {
        if (openN == closedN && openN == n) { // base case
            result.add(s);
        }

        if (openN < n) { // can only add ( when some capacity is left
            backtrack(openN + 1, closedN, n, s + "(");
        }

        if (closedN < openN) { // make sense to add closed only when there are some opened
            backtrack(openN, closedN + 1, n, s + ")");
        }
    }
}
